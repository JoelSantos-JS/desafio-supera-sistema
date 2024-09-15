package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service.Impl;


import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.exceptions.ItemNotFoundException;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.exceptions.ListaNotFoundException;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Items;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Lista;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.repository.ItemsRepository;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.repository.ListaRepository;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service.ItemsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceIMPL implements ItemsService {


    private  final ItemsRepository itemsRepository;
    private final ListaRepository listaRepository;

    public ItemServiceIMPL(ItemsRepository itemsRepository, ListaRepository listaRepository) {
        this.itemsRepository = itemsRepository;
        this.listaRepository = listaRepository;
    }


    @Override
    public Items criarItem(Long listaId, Items item) {
        Lista lista = listaRepository.findById(listaId)
                .orElseThrow(() -> new ListaNotFoundException("Lista não encontrada com ID " + listaId));
        item.setLista(lista);
        return itemsRepository.save(item);
    }

    @Override
    public Optional<Items> obterItemPorId(Long listaId, Long itemId) {
        return itemsRepository.findByIdAndListaId(itemId, listaId);
    }

    @Override
    public List<Items> listarItensPorLista(Long listaId) {
        return itemsRepository.findByListaId( listaId);


    }


    @Transactional
    @Override
    public Items atualizarItem(Long listaId, Long itemId, Items item) {
        if (itemsRepository.existsByIdAndListaId(itemId, listaId)) {
            item.setId(itemId);
            item.setLista(listaRepository.findById(listaId).orElseThrow(() -> new ListaNotFoundException("Lista não encontrada com ID " + listaId)));
            return itemsRepository.save(item);
        } else {
            throw new ItemNotFoundException("Item não encontrado com ID " + itemId + " na lista com ID " + listaId);
        }


    }

    @Override
    public void deletarItem(Long listaId, Long itemId) {

        if (itemsRepository.existsByIdAndListaId(itemId, listaId)) {
            itemsRepository.deleteById(itemId);
        } else {
            throw new ItemNotFoundException("Item não encontrado com ID " + itemId + " na lista com ID " + listaId);
        }

    }

    @Override
    public Items atualizarEstadoItem(Long listaId, Long itemId, String estado) {
        Items item = itemsRepository.findByIdAndListaId(itemId, listaId)
                .orElseThrow(() -> new ItemNotFoundException("Item não encontrado com ID " + itemId + " na lista com ID " + listaId));
        item.setEstado(estado);
        return itemsRepository.save(item);
    }
}
