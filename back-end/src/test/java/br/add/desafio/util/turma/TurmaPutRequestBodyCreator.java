package br.add.desafio.util.turma;

import br.add.desafio.model.Turma;
import br.add.desafio.requests.Turma.TurmaPutRequestBody;

import java.time.LocalDate;

public class TurmaPutRequestBodyCreator {
    public static TurmaPutRequestBody createTurmaPutRequestBody(){
        return TurmaPutRequestBody.builder()
                .id(0)
                .nome("Turma00")
                .capacidade(10)
                .escolaId(0)
                .build();
    }
}
