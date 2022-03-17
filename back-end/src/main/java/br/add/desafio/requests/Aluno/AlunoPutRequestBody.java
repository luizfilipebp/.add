package br.add.desafio.requests.Aluno;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data @AllArgsConstructor
public class AlunoPutRequestBody {
    @NotNull(message = "ID do aluno não pode ser vazio")
    @Min(value = 0, message = "ID do aluno não pode ser negativo")
    private Integer id;

    @NotEmpty(message = "NOME do aluno não pode ser vazio")
    private String nome;

    @NotNull(message = "DATA DE NASCIMENTO do aluno não pode ser vazia")
    private LocalDate dataDeNascimento;

    @NotNull(message = "ID da turma não pode ser vazio")
    @Min(value = 0, message = "ID da turma não pode ser negativo")
    private Integer turmaId;
}