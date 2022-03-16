package br.add.desafio.mapper;

import br.add.desafio.model.Turma;
import br.add.desafio.requests.Turma.TurmaPostRequestBody;
import br.add.desafio.requests.Turma.TurmaPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TurmaMapper {
    public static final TurmaMapper INSTANCE = Mappers.getMapper(TurmaMapper.class);

    public abstract Turma toTurma (TurmaPostRequestBody turmaPostRequestBody);
    public abstract Turma toTurma (TurmaPutRequestBody turmaPutRequestBody);
}
