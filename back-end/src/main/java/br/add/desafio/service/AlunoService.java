package br.add.desafio.service;

import br.add.desafio.exception.BadRequestException;
import br.add.desafio.mapper.AlunoMapper;
import br.add.desafio.model.Aluno;
import br.add.desafio.repository.AlunoRepository;
import br.add.desafio.requests.Aluno.AlunoPostRequestBody;
import br.add.desafio.requests.Aluno.AlunoPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class AlunoService {
    private final AlunoRepository repo;
    private final TurmaService turmaService;
    private final EscolaService escolaService;

    public Aluno findByIDOrThrowBadRequestException(Integer id){
        return repo.findById(id).orElseThrow(() -> new BadRequestException("Aluno não encontrado"));
    }

    @Transactional
    public Aluno save(AlunoPostRequestBody alunoPostRequestBody){
        Aluno aluno = AlunoMapper.INSTANCE.toAluno(alunoPostRequestBody);
        aluno.setTurma(turmaService.findByIDOrThrowBadRequestException(alunoPostRequestBody.getTurmaId()));
        return repo.save(aluno);
    }

    public Page<Aluno> listAll(Pageable pageable){
        return repo.findAll(pageable);
    }

    public void replace(AlunoPutRequestBody alunoPutRequestBody){
        Aluno savedAluno = findByIDOrThrowBadRequestException(alunoPutRequestBody.getId());
        Aluno aluno = AlunoMapper.INSTANCE.toAluno(alunoPutRequestBody);
        aluno.setId(savedAluno.getId());
        aluno.setTurma(turmaService.findByIDOrThrowBadRequestException(alunoPutRequestBody.getId()));

        repo.save(aluno);
    }

    public void delete (Integer id){
        repo.delete(findByIDOrThrowBadRequestException(id));
    }

}
