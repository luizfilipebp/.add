package br.add.desafio.requests.Escola;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class EscolaPostRequestBody {
    private String nome;

}
