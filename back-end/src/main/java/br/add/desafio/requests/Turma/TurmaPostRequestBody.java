package br.add.desafio.requests.Turma;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TurmaPostRequestBody {
    private String nome;
    private int capacidade;
}
