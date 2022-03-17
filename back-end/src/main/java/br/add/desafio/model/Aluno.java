package br.add.desafio.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder

public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nome;
    @Column
    private LocalDate dataDeNascimento;

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;


}
