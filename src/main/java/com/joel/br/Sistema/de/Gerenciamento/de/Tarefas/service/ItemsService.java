package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service;

import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemsService {


    Items criarItem(Long listaId, Items item);
    Optional<Items> obterItemPorId(Long listaId, Long itemId);
    List<Items> listarItensPorLista(Long listaId);
    Items atualizarItem(Long listaId, Long itemId, Items item);
    void deletarItem(Long listaId, Long itemId);
    Items atualizarEstadoItem(Long listaId, Long itemId, String estado);

}
