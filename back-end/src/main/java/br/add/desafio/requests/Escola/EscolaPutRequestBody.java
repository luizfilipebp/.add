package br.add.desafio.requests.Escola;

import br.add.desafio.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @Builder
public class EscolaPutRequestBody {
    @Schema(description = "Id da escola cadastrada", example = "0", minimum = "0")
    @NotNull(message = "ID da escola não pode ser vazio")
    @Min(value = 0, message = "ID da escola não pode ser negativo")
    private Integer id;

    @Schema(description = "Novo nome da escola cadastrada", example = "Escola Técnica de Brasília")
    @NotEmpty(message = "NOME da escola não pode ser vazio")
    private String nome;

    @Schema(description = "Novo endereço da escola cadastrada")
    @NotNull(message = "ENDEREÇO da escola não pode ser vazio")
    private Endereco endereco;

}
