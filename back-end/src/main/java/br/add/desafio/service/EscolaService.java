package br.add.desafio.service;

import br.add.desafio.exception.BadRequestException;
import br.add.desafio.mapper.EscolaMapper;
import br.add.desafio.model.Escola;
import br.add.desafio.repository.EscolaRepository;
import br.add.desafio.requests.Escola.EscolaPostRequestBody;
import br.add.desafio.requests.Escola.EscolaPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class EscolaService {
    private final EscolaRepository repo;

    public Escola findByIDOrThrowBadRequestException(Integer id){
        return repo.findById(id).orElseThrow(() -> new BadRequestException("Escola não encontrado"));
    }

    @Transactional
    public Escola save(EscolaPostRequestBody escolaPostRequestBody){
        return repo.save(EscolaMapper.INSTANCE.toEscola(escolaPostRequestBody));
    }

    public Page<Escola> listAll(Pageable pageable){
        return repo.findAll(pageable);
    }

    public void replace(EscolaPutRequestBody escolaPutRequestBody){
        Escola savedEscola = findByIDOrThrowBadRequestException(escolaPutRequestBody.getId());
        Escola escola = EscolaMapper.INSTANCE.toEscola(escolaPutRequestBody);
        escola.setId(savedEscola.getId());
        repo.save(escola);
    }

    public void delete (Integer id){
        repo.delete(findByIDOrThrowBadRequestException(id));
    }

}
