package com.lucasmafra.tarefasapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank (message = "O nome da tarefa não pode ser vazio.")
    @Size(max = 100, message = "O nome da tarefa deve ter no máximo 100 caracteres.")
    private String nome;

    @Size(max = 500, message = "A descrição da tarefa deve ter no máximo 500 caracteres.")
    private String descricao;

    private LocalDate dataCriacao;

    @Future(message = "O prazo deve ser uma data futura.")
    private LocalDate prazo;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @NotNull(message = "O status da tarefa não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Tarefa(long id, String nome, String descricao, LocalDate dataCriacao, LocalDate prazo, Prioridade prioridade, Status status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.prazo = prazo;
        this.prioridade = prioridade;
        this.status = status;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nome = " + nome + ", " +
                "descricao = " + descricao + ", " +
                "dataCriacao = " + dataCriacao + ", " +
                "prazo = " + prazo + ", " +
                "prioridade = " + prioridade + ", " +
                "status = " + status + ")";
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Tarefa tarefa = (Tarefa) o;
        return getId() != null && Objects.equals(getId(), tarefa.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
