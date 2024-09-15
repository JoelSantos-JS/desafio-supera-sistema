package com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.service;

import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.exceptions.ItemNotFoundException;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.model.Items;
import com.joel.br.Sistema.de.Gerenciamento.de.Tarefas.repository.ItemsRepository;
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
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ItemsServiceTest {

    @Autowired
    private ItemsService itemsService;

    @MockBean
    private ItemsRepository itemsRepository;

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
    public void testCriarItem() {
        when(itemsRepository.save(any(Items.class))).thenAnswer(invocation -> {
            Items savedItem = invocation.getArgument(0);
            savedItem.setId(1L); // Simular ID gerado
            return savedItem;
        });

        Items createdItem = itemsService.criarItem(1L, item);

        Assert.notNull(createdItem, "Item should not be null");
        Assert.isTrue(createdItem.getId().equals(1L), "Item ID should be 1");
    }

    @Test
    public void testObterItemPorId() throws ItemNotFoundException {
        when(itemsRepository.findByIdAndListaId(anyLong(), anyLong())).thenReturn(Optional.of(item));

        Optional<Items> foundItem = itemsService.obterItemPorId(1L, 1L);

        Assert.isTrue(foundItem.isPresent(), "Item should be found");
        Assert.isTrue(foundItem.get().getId().equals(1L), "Item ID should be 1");
    }

    @Test
    public void testListarItensPorLista() {
        List<Items> itemList = new ArrayList<>();
        itemList.add(item);
        when(itemsRepository.findByListaId(anyLong())).thenReturn(itemList);

        List<Items> foundItems = itemsService.listarItensPorLista(1L);

        Assert.notEmpty(foundItems, "Item list should not be empty");
        Assert.isTrue(foundItems.get(0).getId().equals(1L), "Item ID should be 1");
    }

    @Test
    public void testDeletarItem() throws ItemNotFoundException {
        when(itemsRepository.existsByIdAndListaId(anyLong(), anyLong())).thenReturn(true);

        itemsService.deletarItem(1L, 1L);

        verify(itemsRepository, Mockito.times(1)).deleteById(anyLong());
    }

    @Test
    public void testAtualizarEstadoItem() throws ItemNotFoundException {
        Items updatedItem = new Items();
        updatedItem.setId(1L);
        updatedItem.setTitulo("Teste Item");
        updatedItem.setDescricao("Descrição do item");
        updatedItem.setEstado("CONCLUÍDO");
        updatedItem.setPrioridade(0);

        when(itemsRepository.findByIdAndListaId(anyLong(), anyLong())).thenReturn(Optional.of(item));
        when(itemsRepository.save(any(Items.class))).thenReturn(updatedItem);

        Items result = itemsService.atualizarEstadoItem(1L, 1L, "CONCLUÍDO");

        Assert.isTrue(result.getEstado().equals("CONCLUÍDO"), "Item state should be updated to CONCLUÍDO");
    }
}
