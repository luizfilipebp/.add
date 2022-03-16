package br.add.desafio.service;

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

    public Aluno findByIDOrThrowException(Integer id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    @Transactional
    public Aluno save(AlunoPostRequestBody alunoPostRequestBody){
        return repo.save(AlunoMapper.INSTANCE.toAluno(alunoPostRequestBody));
    }

    public Page<Aluno> listAll(Pageable pageable){
        return repo.findAll(pageable);
    }

    public void replace(AlunoPutRequestBody alunoPutRequestBody){
        Aluno savedAluno = findByIDOrThrowException(alunoPutRequestBody.getId());
        Aluno aluno = AlunoMapper.INSTANCE.toAluno(alunoPutRequestBody);
        aluno.setId(savedAluno.getId());
        repo.save(aluno);
    }

    public void delete (Integer id){
        repo.delete(findByIDOrThrowException(id));
    }

}
