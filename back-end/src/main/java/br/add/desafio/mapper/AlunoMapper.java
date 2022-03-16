package br.add.desafio.mapper;

import br.add.desafio.model.Aluno;
import br.add.desafio.requests.Aluno.AlunoPostRequestBody;
import br.add.desafio.requests.Aluno.AlunoPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AlunoMapper {
    public static final AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    public abstract Aluno toAluno (AlunoPostRequestBody alunoPostRequestBody);
    public abstract Aluno toAluno (AlunoPutRequestBody alunoPutRequestBody);
}
