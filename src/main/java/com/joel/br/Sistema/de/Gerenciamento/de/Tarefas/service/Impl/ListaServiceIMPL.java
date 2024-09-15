package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service.Impl;


import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.exceptions.ListaNotFoundException;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Lista;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.repository.ListaRepository;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service.ListaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ListaServiceIMPL implements ListaService {

    private final ListaRepository listaRepository;

    public ListaServiceIMPL(ListaRepository listaRepository) {
        this.listaRepository = listaRepository;
    }

    @Override
    public List<Lista> getAllLista() {


        return  listaRepository.findAll();
    }

    @Override
    public Lista getById(Long id) {
        return listaRepository.findById(id).orElseThrow(() -> {
            throw  new ListaNotFoundException("");
        } );
    }

    @Override
    public Lista createLista(Lista lista) {
        return listaRepository.save(lista);
    }

    @Override
    public Lista updateLista(Long id , Lista lista) {
        Optional<Lista> listaid = listaRepository.findById(id);

        if(listaid.isPresent()) {
            listaid.get().setNome(lista.getNome());
        }
        return listaid.get();
    }

    @Override
    public void deleteByID(Long id) {
        listaRepository.deleteById(id);
    }
}
