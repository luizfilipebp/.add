package br.add.desafio.requests.Aluno;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data @AllArgsConstructor
public class AlunoPostRequestBody {
    @NotEmpty(message = "NOME do aluno nao pode ser vazio")
    private String nome;

    @NotNull(message = "DATA DE NASCIMENTO do aluno nao pode ser vazia")
    private LocalDate dataDeNascimento;

    @NotNull(message = "ID da turma nao pode ser vazio")
    @Min(value = 0, message = "ID da turma não pode ser negativo")
    private Integer turmaId;

}
