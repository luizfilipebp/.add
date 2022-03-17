package br.add.desafio.util.aluno;

import br.add.desafio.model.Turma;
import br.add.desafio.requests.Aluno.AlunoPostRequestBody;

import java.time.LocalDate;

public class AlunoPostRequestBodyCreator {
    public static AlunoPostRequestBody createAlunoPostRequestBody(){
        return AlunoPostRequestBody.builder()
                .nome("Filipe")
                .dataDeNascimento(LocalDate.of(1998,9,24))
                .turmaId(new Turma().getId())
                .build();
    }
}
