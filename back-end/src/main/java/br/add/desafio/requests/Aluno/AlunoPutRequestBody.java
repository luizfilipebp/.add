package br.add.desafio.requests.Aluno;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor

public class AlunoPutRequestBody {
    private int id;
    private String nome;
    private LocalDate dataDeNascimento;
}