package com.lucasmafra.tarefasapi.controller;

import java.util.List;

import com.lucasmafra.tarefasapi.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lucasmafra.tarefasapi.model.Tarefa;

import javax.validation.Valid;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaService.listarTarefas();
    }

    @GetMapping("/{id}")
    public Tarefa buscar(@PathVariable Long id) {
        return tarefaService.buscarPorId(id);
    }

    @PostMapping
    public Tarefa criar(@Valid @RequestBody Tarefa tarefa) {
        return tarefaService.criar(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
        return tarefaService.atualizar(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        tarefaService.remover(id);
    }

}
