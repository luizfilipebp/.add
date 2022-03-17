package br.add.desafio.util.escola;

import br.add.desafio.model.Endereco;
import br.add.desafio.model.Escola;

public class EscolaCreator {
    public static Escola createValidEscola(){
        return Escola.builder()
                .id(0)
                .nome("UCB")
                .endereco(new Endereco())
                .build();
    }

    public static Escola createEscolaToBeSaved() {
        return Escola.builder()
                .id(1)
                .nome("ETB")
                .endereco(new Endereco())
                .build();
    }
}
