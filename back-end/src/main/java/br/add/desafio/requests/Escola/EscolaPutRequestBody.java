package br.add.desafio.requests.Escola;

import br.add.desafio.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor

public class EscolaPutRequestBody {
    @NotEmpty(message = "id não pode ser vazio")
    private int id;

    @NotEmpty(message = "nome não pode ser vazio")
    private String nome;

    @NotNull(message = "endereço nçao pode ser vazio")
    private Endereco endereco;

}
