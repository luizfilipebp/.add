package br.add.desafio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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
