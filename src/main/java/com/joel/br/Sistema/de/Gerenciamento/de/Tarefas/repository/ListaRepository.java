package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.repository;

import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaRepository extends JpaRepository<Lista, Long> {
}
