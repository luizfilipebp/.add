package br.add.desafio.util.turma;

import br.add.desafio.requests.Turma.TurmaPostRequestBody;

public class TurmaPostRequestBodyCreator {
    public static TurmaPostRequestBody createTurmaPostRequestBody(){
        return TurmaPostRequestBody.builder()
                .nome("turma01")
                .capacidade(10)
                .escolaId(1)
                .build();
    }
}
