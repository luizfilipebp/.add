package br.add.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco{
    @Id
    @Column
    private String logradouro;

    @Column
    private String complemento;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String estado;


}
