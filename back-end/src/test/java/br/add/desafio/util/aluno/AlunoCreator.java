package br.add.desafio.util.aluno;

import br.add.desafio.model.Aluno;
import br.add.desafio.model.Turma;

import java.time.LocalDate;

public class AlunoCreator {
    public static Aluno createAlunoToBeSaved(){
        return Aluno.builder()
                .id(1)
                .nome("filipe")
                .dataDeNascimento(LocalDate.of(2022,03,17))
                .turma(new Turma())
                .build();
    }

    public static Aluno createrValidAluno(){
        return Aluno.builder()
                .id(0)
                .nome("luiz")
                .dataDeNascimento(LocalDate.of(2022,03,17))
                .turma(new Turma())
                .build();

    }
}
