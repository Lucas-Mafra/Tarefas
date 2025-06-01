package com.lucasmafra.tarefasapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasmafra.tarefasapi.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {  }
