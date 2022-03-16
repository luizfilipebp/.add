package br.add.desafio.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data

public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String nome;
    @Column
    private LocalDate dataDeNascimento;


}
