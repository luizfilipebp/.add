package br.add.desafio.requests.Turma;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @Builder
public class TurmaPutRequestBody {
    @Schema(description = "ID da turma já cadastrada", example = "0", minimum = "0")
    @NotNull(message = "ID da turma não pode ser vazio")
    @Min(value = 0, message = "ID da turma não pode ser negativo")
    private Integer id;

    @Schema(description = "Novo nome da turma", example = "Turma 002")
    @NotNull(message = "NOME da turma não pode ser vazio")
    private String nome;

    @Schema(description = "Nova capacidade da turma", example = "10", minimum = "1")
    @NotNull(message = "CAPACIDADE da turma não pode ser vazio")
    @Min(value = 1, message = "CAPACIDADE não pode ser menor que 1")
    private Integer capacidade;

    @Schema(description = "ID da escola desta turma", example = "0", minimum = "0")
    @NotNull(message = "ID da escola não pode ser vazio")
    @Min(value = 0, message = "ID da escola não pode ser negativo")
    private Integer escolaId;

}
