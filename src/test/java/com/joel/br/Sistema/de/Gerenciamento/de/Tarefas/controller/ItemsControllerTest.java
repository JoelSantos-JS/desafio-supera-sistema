package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Items;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service.ItemsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemsController.class)
public class ItemsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemsService itemsService;

    private Items item;

    @BeforeEach
    public void setUp() {
        item = new Items();
        item.setId(1L);
        item.setTitulo("Teste Item");
        item.setDescricao("Descrição do item");
        item.setEstado("PENDENTE");
        item.setPrioridade(0);
    }

    @Test
    public void testCriarItem() throws Exception {
        when(itemsService.criarItem(anyLong(), any(Items.class))).thenReturn(item);

        mockMvc.perform(post("/listas/1/itens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Teste Item"));
    }

    @Test
    public void testObterItemPorId() throws Exception {
        when(itemsService.obterItemPorId(anyLong(), anyLong())).thenReturn(Optional.of(item));

        mockMvc.perform(get("/listas/1/itens/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Teste Item"));
    }

    @Test
    public void testListarItensPorLista() throws Exception {
        when(itemsService.listarItensPorLista(anyLong())).thenReturn(Arrays.asList(item));

        mockMvc.perform(get("/listas/1/itens"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].titulo").value("Teste Item"));
    }

    @Test
    public void testAtualizarItem() throws Exception {
        when(itemsService.atualizarItem(anyLong(), anyLong(), any(Items.class))).thenReturn(item);

        mockMvc.perform(put("/listas/1/itens/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Teste Item"));
    }

    @Test
    public void testDeletarItem() throws Exception {
        Mockito.doNothing().when(itemsService).deletarItem(anyLong(), anyLong());

        mockMvc.perform(delete("/listas/1/itens/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAtualizarEstadoItem() throws Exception {
        item.setEstado("CONCLUÍDO");
        when(itemsService.atualizarEstadoItem(anyLong(), anyLong(), any(String.class))).thenReturn(item);

        mockMvc.perform(patch("/listas/1/itens/1/estado?estado=CONCLUÍDO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("CONCLUÍDO"));
    }
}
