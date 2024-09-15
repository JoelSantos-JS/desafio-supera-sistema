package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;




}
