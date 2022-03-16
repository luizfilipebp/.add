package br.add.desafio.mapper;

import br.add.desafio.model.Escola;
import br.add.desafio.requests.Escola.EscolaPostRequestBody;
import br.add.desafio.requests.Escola.EscolaPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class EscolaMapper {
    public static final EscolaMapper INSTANCE = Mappers.getMapper(EscolaMapper.class);

    public abstract Escola toEscola (EscolaPostRequestBody escolaPostRequestBody);
    public abstract Escola toEscola (EscolaPutRequestBody escolaPutRequestBody);
}
