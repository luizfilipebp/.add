package br.add.desafio.service;

import br.add.desafio.exception.BadRequestException;
import br.add.desafio.mapper.TurmaMapper;
import br.add.desafio.model.Turma;
import br.add.desafio.repository.TurmaRepository;
import br.add.desafio.requests.Turma.TurmaPostRequestBody;
import br.add.desafio.requests.Turma.TurmaPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class TurmaService {
    private final TurmaRepository repository;

    public Turma findByIDOrThrowBadRequestException(Integer id){
        return repository.findById(id).orElseThrow(() -> new BadRequestException("Turma não encontrada"));
    }

    @Transactional
    public Turma save(TurmaPostRequestBody turmaPostRequestBody){
        return repository.save(TurmaMapper.INSTANCE.toTurma(turmaPostRequestBody));
    }

    public Page<Turma> listAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public void replace(TurmaPutRequestBody turmaPutRequestBody){
        Turma savedTurma = findByIDOrThrowBadRequestException(turmaPutRequestBody.getId());
        Turma turma = TurmaMapper.INSTANCE.toTurma(turmaPutRequestBody);
        turma.setId(savedTurma.getId());
        repository.save(turma);
    }

    public void delete (Integer id){
        repository.delete(findByIDOrThrowBadRequestException(id));
    }

}
