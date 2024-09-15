package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service;

import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.exceptions.ListaNotFoundException;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Lista;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.repository.ListaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ListaServiceTest {

    @Autowired
    private ListaService listasService;

    @MockBean
    private ListaRepository listasRepository;

    private Lista lista;

    @BeforeEach
    public void setUp() {
        lista = new Lista();
        lista.setId(1L);
        lista.setNome("Teste Lista");
    }

    @Test
    public void testCriarLista() {
        when(listasRepository.save(any(Lista.class))).thenReturn(lista);

        Lista createdLista = listasService.createLista(lista);

        Assert.notNull(createdLista, "Lista should not be null");
        Assert.isTrue(createdLista.getId().equals(1L), "Lista ID should be 1");
    }

    @Test
    public void testObterListaPorId() {
        when(listasRepository.findById(anyLong())).thenReturn(Optional.of(lista));

        Optional<Lista> foundLista = Optional.ofNullable(listasService.getById(1L));

        Assert.isTrue(foundLista.isPresent(), "Lista should be found");
        Assert.isTrue(foundLista.get().getId().equals(1L), "Lista ID should be 1");
    }

    @Test
    public void testListarListas() {
        List<Lista> listasList = new ArrayList<>();
        listasList.add(lista);
        when(listasRepository.findAll()).thenReturn(listasList);

        List<Lista> foundListas = listasService.getAllLista();

        Assert.notEmpty(foundListas, "Listas list should not be empty");
        Assert.isTrue(foundListas.get(0).getId().equals(1L), "Lista ID should be 1");
    }

    @Test
    public void testAtualizarLista() {
        Lista updatedLista = new Lista();
        updatedLista.setId(1L);
        updatedLista.setNome("Lista Atualizada");

        when(listasRepository.findById(anyLong())).thenReturn(Optional.of(lista));
        when(listasRepository.save(any(Lista.class))).thenReturn(updatedLista);

        Lista result = listasService.updateLista(1L, updatedLista);

        Assert.isTrue(result.getNome().equals("Lista Atualizada"), "Lista title should be updated");
    }

    @Test
    public void testDeletarLista() {
        when(listasRepository.findById(anyLong())).thenReturn(Optional.of(lista));

        listasService.deleteByID(1L);

        Mockito.verify(listasRepository, Mockito.times(1)).deleteById(anyLong());
    }
}
