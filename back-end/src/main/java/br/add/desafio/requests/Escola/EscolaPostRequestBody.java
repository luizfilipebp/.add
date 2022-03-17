package br.add.desafio.requests.Escola;

import br.add.desafio.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor

public class EscolaPostRequestBody {
    @NotEmpty(message = "NOME da escola nao pode ser vazio")
    private String nome;

    @NotNull(message = "ENDEREÇO da escola nao pode ser vazio")
    private Endereco endereco;

}
