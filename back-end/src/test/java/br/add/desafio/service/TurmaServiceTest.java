package br.add.desafio.service;

import br.add.desafio.exception.BadRequestException;
import br.add.desafio.model.Turma;
import br.add.desafio.repository.TurmaRepository;
import br.add.desafio.util.turma.TurmaCreator;
import br.add.desafio.util.turma.TurmaPostRequestBodyCreator;
import br.add.desafio.util.turma.TurmaPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste para Turma Service")
class TurmaServiceTest {
    @InjectMocks
    private TurmaService turmaService;

    @Mock
    private TurmaRepository turmaRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Turma> turmaPage = new PageImpl<>(List.of(TurmaCreator.createrValidTurma()));

        BDDMockito.when(turmaRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(turmaPage);

        BDDMockito.when(turmaRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(TurmaCreator.createrValidTurma()));

        BDDMockito.when(turmaRepositoryMock.save(ArgumentMatchers.any(Turma.class)))
                .thenReturn(TurmaCreator.createrValidTurma());

        BDDMockito.doNothing().when(turmaRepositoryMock).delete(ArgumentMatchers.any(Turma.class));

    }
    @Test
    @DisplayName("findById, retorna Turma quando tem sucesso")
    void findById_ReturnsTurma_WhenSuccessful() {
        int expectedId = TurmaCreator.createrValidTurma().getId();

        Turma turma = turmaService.findByIDOrThrowBadRequestException(1);

        Assertions.assertThat(turma).isNotNull();
        Assertions.assertThat(turma.getId()).isNotNull().isEqualTo(expectedId);
    }


    @Test
    @DisplayName("findByIDOrThrowBadRequestException, Lança uma bad request exception quando o turma não é encontrado")
    void findByIDOrThrowBadRequestException_ThrowsBadRequestException_WhenTurmaIsNotFound() {
        BDDMockito.when(turmaRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> turmaService.findByIDOrThrowBadRequestException(1));

    }

    @Test
    @DisplayName("Save, retorna turma quando tem sucesso")
    void save_ReturnsTurma_WhenSuccessful() {
        Turma turma = turmaService.save(TurmaPostRequestBodyCreator.createTurmaPostRequestBody());

        Assertions.assertThat(turma).isNotNull().isEqualTo(TurmaCreator.createrValidTurma());
    }

    @Test
    @DisplayName("ListAll, retorna uma listas de turmas quando tem sucesso")
    void listAll_ReturnsListOfTurma_WhenSuccessful(){
        String expectedName = TurmaCreator.createrValidTurma().getNome();

        Page<Turma> turmaPage = turmaService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(turmaPage).isNotNull();

        Assertions.assertThat(turmaPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(turmaPage.toList().get(0).getNome()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("Replace, atualiza um turma quando tem sucesso")
    void replace_UpdateTurma_WhenSuccessful() {
        Assertions.assertThatCode(() -> turmaService.replace(TurmaPutRequestBodyCreator.createTurmaPutRequestBody()))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("Delete, remove um turma quando tem sucesso")
    void delete_RemovesTurma_WhenSuccessful() {

        Assertions.assertThatCode(() -> turmaService.delete(1))
                .doesNotThrowAnyException();
    }
}