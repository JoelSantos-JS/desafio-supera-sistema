package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service;

import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Lista;

import java.util.ArrayList;
import java.util.List;

public interface ListaService {

    List<Lista> getAllListas = new ArrayList<>();

    List<Lista> getAllLista();

    Lista getById(Long id);

    Lista createLista(Lista itemsDTO);

    Lista  updateLista(Long id , Lista itemsDTO);

    void  deleteByID(Long id);


}
