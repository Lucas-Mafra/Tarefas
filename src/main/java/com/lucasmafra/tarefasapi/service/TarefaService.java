package com.lucasmafra.tarefasapi.service;

import com.lucasmafra.tarefasapi.Repository.TarefaRepository;
import com.lucasmafra.tarefasapi.exception.TarefaNaoEncontradaException;
import com.lucasmafra.tarefasapi.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa criar(Tarefa tarefa) {
        if(tarefa.getNome() == null || tarefa.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da tarefa não pode ser vazio.");
        }

        tarefa.setDataCriacao(LocalDate.now());
        return tarefaRepository.save(tarefa);
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id).orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }

    public Tarefa atualizar(Long id, Tarefa novaTarefa) {
        if(novaTarefa.getNome() == null || novaTarefa.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da tarefa não pode ser vazio.");
        }

        return tarefaRepository.findById(id).map(
                tarefa -> {
                    tarefa.setNome(novaTarefa.getNome());
                    tarefa.setDescricao(novaTarefa.getDescricao());
                    tarefa.setDataCriacao(novaTarefa.getDataCriacao());
                    tarefa.setPrazo(novaTarefa.getPrazo());
                    tarefa.setStatus(novaTarefa.getStatus());
                    tarefa.setPrioridade(novaTarefa.getPrioridade());
                    return tarefaRepository.save(tarefa);
                }
        ).orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }

    public void remover(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new TarefaNaoEncontradaException(id));
        tarefaRepository.delete(tarefa);
    }
}
