package br.add.desafio.util.escola;

import br.add.desafio.model.Endereco;
import br.add.desafio.requests.Escola.EscolaPostRequestBody;

public class EscolaPostRequestBodyCreator {
    public static EscolaPostRequestBody createEscolaPostRequestBody(){
        return EscolaPostRequestBody.builder()
                .nome("UCB")
                .endereco(new Endereco())
                .build();
    }
}
