package br.add.desafio.requests.Turma;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor
public class TurmaPostRequestBody {
    @Schema(description = "Nomme da turma", example = "Turma 001")
    @NotEmpty(message = "NOME da turma nao pode ser vazio")
    private String nome;

    @Schema(description = "Capacidade da turma", example = "35", minimum = "1")
    @NotNull(message = "CAPACIDADE da turma não pode ser vazia")
    @Min(value = 1, message = "CAPACIDADE não pode ser menor que 1")
    private Integer capacidade;

    @Schema(description = "ID da escola desta turma", example = "0", minimum = "0")
    @NotNull(message = "ID da escola não pode ser vazio")
    @Min(value = 0, message = "ID da escola não pode ser negativo")
    private Integer escolaId;

}
