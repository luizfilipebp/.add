package br.add.desafio.util.escola;

import br.add.desafio.model.Endereco;
import br.add.desafio.requests.Escola.EscolaPutRequestBody;

public class EscolaPutRequestBodyCreator {
    public static EscolaPutRequestBody createEscolaPutRequestBody(){
        return EscolaPutRequestBody.builder()
                .id(1)
                .nome("UCB")
                .endereco(new Endereco())
                .build();
    }
}
