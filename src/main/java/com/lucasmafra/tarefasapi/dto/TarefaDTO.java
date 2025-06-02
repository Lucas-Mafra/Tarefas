package com.lucasmafra.tarefasapi.dto;

public class TarefaDTO {
    private String nome;
    private String descricao;
    private String prazo;
    private String prioridade;
    private String status;

    public TarefaDTO() {
    }

    public TarefaDTO(String nome, String descricao, String dataCriacao, String prazo, String prioridade, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.prazo = prazo;
        this.prioridade = prioridade;
        this.status = status;
    }

    // Getters and Setters
}
