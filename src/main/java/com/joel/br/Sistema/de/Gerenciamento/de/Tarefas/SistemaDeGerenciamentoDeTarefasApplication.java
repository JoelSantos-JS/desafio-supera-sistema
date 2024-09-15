package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Sistema de Gerenciamento de Tarefas API", version = "1", description = "API para gerenciamento de tarefas e listas, permitindo operações de criação, leitura, atualização e deleção de itens e listas."))
public class SistemaDeGerenciamentoDeTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeGerenciamentoDeTarefasApplication.class, args);
	}

}
