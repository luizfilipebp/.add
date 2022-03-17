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
public class AlunoPostRequestBody {
    @Schema(description = "Nome do aluno", example = "Filipe")
    @NotEmpty(message = "NOME do aluno nao pode ser vazio")
    private String nome;

    @Schema(description = "Data de nascimento do aluno", example = "1998-09-24")
    @NotNull(message = "DATA DE NASCIMENTO do aluno nao pode ser vazia")
    private LocalDate dataDeNascimento;

    @Schema(description = "Id de uma tuma já cadastrada", example = "1", minimum = "0")
    @NotNull(message = "ID da turma nao pode ser vazio")
    @Min(value = 0, message = "ID da turma não pode ser negativo")
    private Integer turmaId;

}
