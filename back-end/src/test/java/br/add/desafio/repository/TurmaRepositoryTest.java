package br.add.desafio.repository;

import br.add.desafio.model.Turma;
import br.add.desafio.util.turma.TurmaCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Testes para o Turma Repository")
class TurmaRepositoryTest {
    @Autowired
    private TurmaRepository turmaRepository;

    @Test
    @DisplayName("Save, faz a persistência de um turma quando tem sucesso")
    public void save_PersistTurma_WhenSuccessful(){
        Turma turmaToBeSaved = TurmaCreator.createTurmaToBeSaved();
        Turma savedTurma = this.turmaRepository.save(turmaToBeSaved);
        Assertions.assertThat(savedTurma).isNotNull();
        Assertions.assertThat(savedTurma.getId()).isNotNull();
        Assertions.assertThat(savedTurma.getNome()).isEqualTo(turmaToBeSaved.getNome());
    }

    @Test
    @DisplayName("Save, atualiza um turma quando tem sucesso")
    public void save_UpdatesTurma_WhenSuccessful(){
        Turma turmaToBeSaved = TurmaCreator.createTurmaToBeSaved();
        Turma turmaSaved = this.turmaRepository.save(turmaToBeSaved);

        turmaSaved.setNome("SaveUpdatedTest");

        Turma turmaUpdated = this.turmaRepository.save(turmaSaved);

        Assertions.assertThat(turmaUpdated).isNotNull();
        Assertions.assertThat(turmaUpdated.getId()).isNotNull();
        Assertions.assertThat(turmaUpdated.getNome()).isEqualTo(turmaSaved.getNome());
    }

    @Test
    @DisplayName("Delete, removes user when successful")
    public void delete_RemovesUser_WhenSuccessful(){
        Turma turmaToBeSaved = TurmaCreator.createTurmaToBeSaved();
        Turma turmaSaved = this.turmaRepository.save(turmaToBeSaved);

        this.turmaRepository.delete(turmaSaved);
        Optional<Turma> turmaOptional = this.turmaRepository.findById(turmaSaved.getId());
        Assertions.assertThat(turmaOptional).isEmpty();
    }
}