package br.add.desafio.requests.Turma;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor
public class TurmaPostRequestBody {
    @NotEmpty(message = "NOME da turma nao pode ser vazio")
    private String nome;

    @NotNull(message = "CAPACIDADE da turma não pode ser vazia")
    @Min(value = 1, message = "CAPACIDADE não pode ser menor que 1")
    private Integer capacidade;

    @NotNull(message = "ID da escola não pode ser vazio")
    @Min(value = 0, message = "ID da escola não pode ser negativo")
    private Integer escolaId;

}
