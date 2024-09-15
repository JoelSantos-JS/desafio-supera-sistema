package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.controller;

import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.exceptions.ItemNotFoundException;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Items;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service.ItemsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/listas/{listaId}/itens")
@Tag(name = "Itens", description = "Operações relacionadas aos itens em uma lista")
public class ItemsController {

    private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Operation(summary = "Cria um novo item na lista", description = "Cria um novo item com base nos detalhes fornecidos e o adiciona à lista especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "404", description = "Lista não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar o item")
    })
    @PostMapping
    public ResponseEntity<Items> criarItem(@PathVariable Long listaId, @RequestBody Items item) {
        return ResponseEntity.ok(itemsService.criarItem(listaId, item));
    }

    @Operation(summary = "Obtém um item por ID", description = "Recupera um item específico com base no ID fornecido na lista especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado com o ID fornecido na lista especificada"),
            @ApiResponse(responseCode = "500", description = "Erro ao obter o item")
    })
    @GetMapping("/{itemId}")
    public ResponseEntity<Items> obterItemPorId(@PathVariable Long listaId, @PathVariable Long itemId) {
        return itemsService.obterItemPorId(listaId, itemId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ItemNotFoundException("Item não encontrado com ID " + itemId + " na lista com ID " + listaId));
    }

    @Operation(summary = "Lista todos os itens de uma lista", description = "Retorna todos os itens da lista especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens listados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar os itens")
    })
    @GetMapping
    public ResponseEntity<List<Items>> listarItensPorLista(@PathVariable Long listaId) {
        return ResponseEntity.ok(itemsService.listarItensPorLista(listaId));
    }

    @Operation(summary = "Atualiza um item existente", description = "Atualiza os detalhes de um item específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "404", description = "Item ou lista não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o item")
    })
    @PutMapping("/{itemId}")
    public ResponseEntity<Items> atualizarItem(@PathVariable Long listaId, @PathVariable Long itemId, @RequestBody Items item) {
        return ResponseEntity.ok(itemsService.atualizarItem(listaId, itemId, item));
    }

    @Operation(summary = "Deleta um item", description = "Remove um item específico da lista com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item ou lista não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar o item")
    })
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long listaId, @PathVariable Long itemId) {
        itemsService.deletarItem(listaId, itemId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza o estado de um item", description = "Atualiza o estado de um item específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado do item atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos"),
            @ApiResponse(responseCode = "404", description = "Item ou lista não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar o estado do item")
    })
    @PatchMapping("/{itemId}/estado")
    public ResponseEntity<Items> atualizarEstadoItem(@PathVariable Long listaId, @PathVariable Long itemId, @RequestParam String estado) {
        return ResponseEntity.ok(itemsService.atualizarEstadoItem(listaId, itemId, estado));
    }
}
