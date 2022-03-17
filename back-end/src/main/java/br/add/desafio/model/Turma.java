package br.add.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String nome;

    @Column
    private int capacidade;

    @ManyToOne
    @JoinColumn(name = "id_escola")
    private Escola escola;

    @OneToMany(mappedBy = "turma", orphanRemoval = true)
    private List<Aluno> alunos;
}
