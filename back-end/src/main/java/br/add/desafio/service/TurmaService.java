package br.add.desafio.service;

import br.add.desafio.exception.BadRequestException;
import br.add.desafio.mapper.TurmaMapper;
import br.add.desafio.model.Escola;
import br.add.desafio.model.Turma;
import br.add.desafio.repository.EscolaRepository;
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
    private final TurmaRepository turmaRepository;
    private final EscolaService escolaService;

    public Turma findByIDOrThrowBadRequestException(Integer id){
        return turmaRepository.findById(id).orElseThrow(() -> new BadRequestException("Turma não encontrada"));
    }

    @Transactional
    public Turma save(TurmaPostRequestBody turmaPostRequestBody){
        Turma turma = TurmaMapper.INSTANCE.toTurma(turmaPostRequestBody);
        if (turma.getCapacidade() < 1) throw new BadRequestException("A capacidade da turma nao pode ser menor que 1");

        Escola escola = escolaService.findByIDOrThrowBadRequestException(turmaPostRequestBody.getEscolaId());
        turma.setEscola(escola);

        return turmaRepository.save(turma);
    }

    public Page<Turma> listAll(Pageable pageable){
        return turmaRepository.findAll(pageable);
    }

    public void replace(TurmaPutRequestBody turmaPutRequestBody){
        Turma savedTurma = findByIDOrThrowBadRequestException(turmaPutRequestBody.getId());
        Turma turma = TurmaMapper.INSTANCE.toTurma(turmaPutRequestBody);
        turma.setId(savedTurma.getId());
        turma.setEscola(escolaService.findByIDOrThrowBadRequestException(turmaPutRequestBody.getEscolaId()));
        turmaRepository.save(turma);
    }

    public void delete (Integer id){
        turmaRepository.delete(findByIDOrThrowBadRequestException(id));
    }

}
