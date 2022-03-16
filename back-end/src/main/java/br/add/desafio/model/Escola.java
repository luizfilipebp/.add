package br.add.desafio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String nome;

    @OneToMany(mappedBy = "escola")
    private List<Turma> turmas;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;


}
