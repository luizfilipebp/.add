package br.add.desafio.repository;

import br.add.desafio.model.Aluno;
import br.add.desafio.util.aluno.AlunoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Testes para o Aluno Repository")
class AlunoRepositoryTest {
    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Save, faz a persistência de um aluno quando tem sucesso")
    public void save_PersistAluno_WhenSuccessful(){
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();
        Aluno savedAluno = this.alunoRepository.save(alunoToBeSaved);
        Assertions.assertThat(savedAluno).isNotNull();
        Assertions.assertThat(savedAluno.getId()).isNotNull();
        Assertions.assertThat(savedAluno.getNome()).isEqualTo(alunoToBeSaved.getNome());
    }

    @Test
    @DisplayName("Save, atualiza um aluno quando tem sucesso")
    public void save_UpdatesAluno_WhenSuccessful(){
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();
        Aluno alunoSaved = this.alunoRepository.save(alunoToBeSaved);

        alunoSaved.setNome("SaveUpdatedTest");

        Aluno alunoUpdated = this.alunoRepository.save(alunoSaved);

        Assertions.assertThat(alunoUpdated).isNotNull();
        Assertions.assertThat(alunoUpdated.getId()).isNotNull();
        Assertions.assertThat(alunoUpdated.getNome()).isEqualTo(alunoSaved.getNome());
    }

    @Test
    @DisplayName("Delete, removes user when successful")
    public void delete_RemovesUser_WhenSuccessful(){
        Aluno alunoToBeSaved = AlunoCreator.createAlunoToBeSaved();
        Aluno alunoSaved = this.alunoRepository.save(alunoToBeSaved);

        this.alunoRepository.delete(alunoSaved);
        Optional<Aluno> alunoOptional = this.alunoRepository.findById(alunoSaved.getId());
        Assertions.assertThat(alunoOptional).isEmpty();
    }
}