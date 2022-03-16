package br.add.desafio.requests.Escola;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class EscolaPutRequestBody {
    private int id;
    private String nome;

}
