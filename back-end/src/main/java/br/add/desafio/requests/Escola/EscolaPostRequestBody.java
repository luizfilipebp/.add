package br.add.desafio.requests.Escola;

import br.add.desafio.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor

public class EscolaPostRequestBody {
    @Schema(description = "Nome da escola", example = "Universidade Católica de Brasília")
    @NotEmpty(message = "NOME da escola nao pode ser vazio")
    private String nome;

    @Schema(description = "Endereço da escola")
    @NotNull(message = "ENDEREÇO da escola nao pode ser vazio")
    private Endereco endereco;

}
