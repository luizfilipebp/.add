package br.add.desafio.repository;

import br.add.desafio.model.Escola;
import br.add.desafio.util.escola.EscolaCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Testes para o Escola Repository")
class EscolaRepositoryTest {
    @Autowired
    private EscolaRepository escolaRepository;

    @Test
    @DisplayName("Save, faz a persistência de um escola quando tem sucesso")
    public void save_PersistEscola_WhenSuccessful(){
        Escola escolaToBeSaved = EscolaCreator.createEscolaToBeSaved();
        Escola savedEscola = this.escolaRepository.save(escolaToBeSaved);
        Assertions.assertThat(savedEscola).isNotNull();
        Assertions.assertThat(savedEscola.getId()).isNotNull();
        Assertions.assertThat(savedEscola.getNome()).isEqualTo(escolaToBeSaved.getNome());
    }

    @Test
    @DisplayName("Save, atualiza um escola quando tem sucesso")
    public void save_UpdatesEscola_WhenSuccessful(){
        Escola escolaToBeSaved = EscolaCreator.createEscolaToBeSaved();
        Escola escolaSaved = this.escolaRepository.save(escolaToBeSaved);

        escolaSaved.setNome("SaveUpdatedTest");

        Escola escolaUpdated = this.escolaRepository.save(escolaSaved);

        Assertions.assertThat(escolaUpdated).isNotNull();
        Assertions.assertThat(escolaUpdated.getId()).isNotNull();
        Assertions.assertThat(escolaUpdated.getNome()).isEqualTo(escolaSaved.getNome());
    }

    @Test
    @DisplayName("Delete, removes user when successful")
    public void delete_RemovesUser_WhenSuccessful(){
        Escola escolaToBeSaved = EscolaCreator.createEscolaToBeSaved();
        Escola escolaSaved = this.escolaRepository.save(escolaToBeSaved);

        this.escolaRepository.delete(escolaSaved);
        Optional<Escola> escolaOptional = this.escolaRepository.findById(escolaSaved.getId());
        Assertions.assertThat(escolaOptional).isEmpty();
    }
}