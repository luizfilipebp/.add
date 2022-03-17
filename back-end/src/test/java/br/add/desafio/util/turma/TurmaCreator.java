package br.add.desafio.util.turma;

import br.add.desafio.model.Escola;
import br.add.desafio.model.Turma;
import br.add.desafio.model.Turma;

import java.time.LocalDate;

public class TurmaCreator {
    public static Turma createTurmaToBeSaved(){
        return Turma.builder()
                .id(1)
                .nome("turma01")
                .capacidade(1)
                .escola(new Escola())
                .build();
    }

    public static Turma createrValidTurma(){
        return Turma.builder()
                .id(0)
                .nome("turma00")
                .capacidade(0)
                .escola(new Escola())
                .build();

    }
}
