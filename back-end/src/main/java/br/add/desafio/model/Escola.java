package br.add.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Escola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String nome;

    @OneToMany(mappedBy = "escola", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turma> turmas;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;


}
