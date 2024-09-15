package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.controller;

import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Lista;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lista")
@Tag(name = "Listas", description = "Operações relacionadas às listas")
public class ListaController {

    private final ListaService listaService;

    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @Operation(summary = "Cria uma nova lista", description = "Cria uma nova lista com base nos detalhes fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar a lista")
    })
    @PostMapping
    public ResponseEntity<Lista> criarLista(@RequestBody Lista lista) {
        return ResponseEntity.ok(listaService.createLista(lista));
    }

    @Operation(summary = "Obtém uma lista por ID", description = "Recupera uma lista específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista não encontrada com o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro ao obter a lista")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Lista> obterListaPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(listaService.getById(id));
    }

    @Operation(summary = "Lista todas as listas", description = "Retorna todas as listas disponíveis.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listas listadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar as listas")
    })
    @GetMapping
    public ResponseEntity<List<Lista>> listarListas() {
        return ResponseEntity.ok(listaService.getAllLista());
    }

    @Operation(summary = "Atualiza uma lista existente", description = "Atualiza os detalhes de uma lista específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "404", description = "Lista não encontrada com o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar a lista")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Lista> atualizarLista(@PathVariable Long id, @RequestBody Lista lista) {
        return ResponseEntity.ok(listaService.updateLista(id, lista));
    }

    @Operation(summary = "Deleta uma lista", description = "Remove uma lista específica com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Lista deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista não encontrada com o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar a lista")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLista(@PathVariable Long id) {
        listaService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }
}
