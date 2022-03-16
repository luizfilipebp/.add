package br.add.desafio.model;

import lombok.Data;

import javax.persistence.*;

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
}
