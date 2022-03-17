package br.add.desafio.util.aluno;

import br.add.desafio.model.Turma;
import br.add.desafio.requests.Aluno.AlunoPostRequestBody;
import br.add.desafio.requests.Aluno.AlunoPutRequestBody;

import java.time.LocalDate;

public class AlunoPutRequestBodyCreator {
    public static AlunoPutRequestBody createAlunoPutRequestBody(){
        return AlunoPutRequestBody.builder()
                .id(1)
                .nome("Filipe")
                .dataDeNascimento(LocalDate.of(1998,9,24))
                .turmaId(new Turma().getId())
                .build();
    }
}
