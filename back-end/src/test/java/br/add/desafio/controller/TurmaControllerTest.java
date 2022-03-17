package br.add.desafio.controller;

import br.add.desafio.model.Turma;
import br.add.desafio.requests.Turma.TurmaPostRequestBody;
import br.add.desafio.service.TurmaService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes para Turma Controller")
class TurmaControllerTest {
    @InjectMocks
    private TurmaController turmaController;

    @Mock
    private TurmaService turmaServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Turma> turmasPage = new PageImpl<>(List.of(TurmaCreator.createrValidTurma()));
        BDDMockito.when(turmaServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(turmasPage);

        BDDMockito.when(turmaServiceMock.save(ArgumentMatchers.any(TurmaPostRequestBody.class)))
                .thenReturn(TurmaCreator.createrValidTurma());

        BDDMockito.doNothing().when(turmaServiceMock).delete(ArgumentMatchers.anyInt());
    }

    @Test
    @DisplayName("List, retorna uma lista de turmas dentro de um page object quando tem sucesso")
    void list_ReturnsListOfTurmaInsidePageObject_WhenSuccessful() {
        String expectedNome = TurmaCreator.createrValidTurma().getNome();
        Page<Turma> turmaPage = turmaController.list(null).getBody();

        Assertions.assertThat(turmaPage).isNotNull();

        Assertions.assertThat(turmaPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(turmaPage.toList().get(0).getNome()).isEqualTo(expectedNome);
    }

    @DisplayName("ListAll, retorna uma lista de turmas quando tem sucesso")
    void listAll_ReturnsListOfTurmas_WhenSuccessful(){
        String expectedNome = TurmaCreator.createrValidTurma().getNome();
        Page<Turma> turmaPage = turmaController.list(null).getBody();

        Assertions.assertThat(turmaPage).isNotNull();

        Assertions.assertThat(turmaPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(turmaPage.toList().get(0).getNome()).isEqualTo(expectedNome);
    }


    @Test
    @DisplayName("Save, retorna um Turma quando tem sucesso")
    void save_ReturnsTurma_WhenSuccessful() {
        int expectedId = TurmaCreator.createrValidTurma().getId();

        Turma turma = turmaController.save(TurmaPostRequestBodyCreator.createTurmaPostRequestBody()).getBody();

        Assertions.assertThat(turma).isNotNull().isEqualTo(TurmaCreator.createrValidTurma());
    }

    @Test
    @DisplayName("Replace, atualiza um turma quando tem sucesso")
    void replace_UpdateTurma_WhenSuccessful(){
        Assertions.assertThatCode(() -> turmaController.replace(TurmaPutRequestBodyCreator.createTurmaPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = turmaController.replace(TurmaPutRequestBodyCreator.createTurmaPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Delete, remove um turma quando tem sucesso")
    void delete_RemovesTurma_WhenSuccessful(){
        Assertions.assertThatCode(() -> turmaController.delete(1)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = turmaController.delete(1);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}