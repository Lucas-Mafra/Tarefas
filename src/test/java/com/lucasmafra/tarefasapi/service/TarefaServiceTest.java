package com.lucasmafra.tarefasapi.service;


import com.lucasmafra.tarefasapi.Repository.TarefaRepository;
import com.lucasmafra.tarefasapi.exception.TarefaNaoEncontradaException;
import com.lucasmafra.tarefasapi.model.Prioridade;
import com.lucasmafra.tarefasapi.model.Status;
import com.lucasmafra.tarefasapi.model.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TarefaServiceTest {

    @MockBean
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarPorId() {
        Tarefa tarefa = new Tarefa(
                1L,
                "Estudar",
                "Estudar Spring Boot",
                LocalDate.now(), // Data de criação
                LocalDate.now().plusDays(6), // Prazo
                Prioridade.BAIXA,
                Status.PENDENTE
        );

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        Tarefa resultado = tarefaService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Estudar", resultado.getNome());
    }

    @Test
    public void testExcecaoTarefaNaoEncontrada() {
        when(tarefaRepository.findById(999999L)).thenReturn(Optional.empty());

        assertThrows(TarefaNaoEncontradaException.class, () -> {
            tarefaService.buscarPorId(999999L);
        });
    }



}
