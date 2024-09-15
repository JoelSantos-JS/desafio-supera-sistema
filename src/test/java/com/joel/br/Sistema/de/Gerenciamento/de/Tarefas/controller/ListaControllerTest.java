package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Lista;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service.ListaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ListaController.class)
public class ListaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListaService listaService;

    private Lista lista;

    @BeforeEach
    public void setUp() {
        lista = new Lista();
        lista.setId(1L);
        lista.setNome("Teste Lista");
    }

    @Test
    public void testCriarLista() throws Exception {
        when(listaService.createLista(any(Lista.class))).thenReturn(lista);

        mockMvc.perform(post("/lista")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lista)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Teste Lista"));
    }

    @Test
    public void testObterListaPorId() throws Exception {
        when(listaService.getById(anyLong())).thenReturn(lista);

        mockMvc.perform(get("/lista/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Teste Lista"));
    }

    @Test
    public void testListarTodasListas() throws Exception {
        when(listaService.getAllLista()).thenReturn(Arrays.asList(lista));

        mockMvc.perform(get("/lista"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Teste Lista"));
    }

    @Test
    public void testAtualizarLista() throws Exception {
        when(listaService.updateLista(anyLong(), any(Lista.class))).thenReturn(lista);

        mockMvc.perform(put("/lista/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lista)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Teste Lista"));
    }

    @Test
    public void testDeletarLista() throws Exception {
        Mockito.doNothing().when(listaService).deleteByID(anyLong());

        mockMvc.perform(delete("/lista/1"))
                .andExpect(status().isNoContent());
    }
}
