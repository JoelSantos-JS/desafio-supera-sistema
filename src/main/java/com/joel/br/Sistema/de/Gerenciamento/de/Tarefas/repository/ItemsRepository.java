package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.repository;

import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemsRepository extends JpaRepository<Items, Long> {

    List<Items> findByListaId(Long listaId);
    Optional<Items> findByIdAndListaId(Long itemId, Long listaId);
    Boolean existsByIdAndListaId(Long itemId,Long listaId);
}
