package br.add.desafio.requests.Escola;

import br.add.desafio.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor
public class EscolaPutRequestBody {
    @NotNull(message = "ID da escola não pode ser vazio")
    @Min(value = 0, message = "ID da escola não pode ser negativo")
    private Integer id;

    @NotEmpty(message = "NOME da escola não pode ser vazio")
    private String nome;

    @NotNull(message = "ENDEREÇO da escola não pode ser vazio")
    private Endereco endereco;

}
