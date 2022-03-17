package br.add.desafio.requests.Aluno;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data @AllArgsConstructor @Builder
public class AlunoPutRequestBody {
    @Schema(description = "Id do aluno já cadastrado", example = "1", minimum = "0")
    @NotNull(message = "ID do aluno não pode ser vazio")
    @Min(value = 0, message = "ID do aluno não pode ser negativo")
    private Integer id;

    @Schema(description = "Novo nome do aluno", example = "Luiz")
    @NotEmpty(message = "NOME do aluno não pode ser vazio")
    private String nome;

    @Schema(description = "Nova data de nascimento do aluno", example = "2022-03-16")
    @NotNull(message = "DATA DE NASCIMENTO do aluno não pode ser vazia")
    private LocalDate dataDeNascimento;

    @Schema(description = "Nova turma do aluno passando ID", example = "2", minimum = "0")
    @NotNull(message = "ID da turma não pode ser vazio")
    @Min(value = 0, message = "ID da turma não pode ser negativo")
    private Integer turmaId;
}