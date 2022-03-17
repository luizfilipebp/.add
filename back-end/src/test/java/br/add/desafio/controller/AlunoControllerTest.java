package br.add.desafio.controller;

import br.add.desafio.model.Aluno;
import br.add.desafio.requests.Aluno.AlunoPostRequestBody;
import br.add.desafio.service.AlunoService;
import br.add.desafio.util.aluno.AlunoCreator;
import br.add.desafio.util.aluno.AlunoPostRequestBodyCreator;
import br.add.desafio.util.aluno.AlunoPutRequestBodyCreator;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes para Aluno Controller")
class AlunoControllerTest {
    @InjectMocks
    private AlunoController alunoController;

    @Mock
    private AlunoService alunoServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Aluno> alunosPage = new PageImpl<>(List.of(AlunoCreator.createrValidAluno()));
        BDDMockito.when(alunoServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(alunosPage);

        BDDMockito.when(alunoServiceMock.save(ArgumentMatchers.any(AlunoPostRequestBody.class)))
                .thenReturn(AlunoCreator.createrValidAluno());

        BDDMockito.doNothing().when(alunoServiceMock).delete(ArgumentMatchers.anyInt());
    }

    @Test
    @DisplayName("List, retorna uma lista de alunos dentro de um page object quando tem sucesso")
    void list_ReturnsListOfAlunoInsidePageObject_WhenSuccessful() {
        String expectedNome = AlunoCreator.createrValidAluno().getNome();
        Page<Aluno> alunoPage = alunoController.list(null).getBody();

        Assertions.assertThat(alunoPage).isNotNull();

        Assertions.assertThat(alunoPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(alunoPage.toList().get(0).getNome()).isEqualTo(expectedNome);
    }

    @DisplayName("ListAll, retorna uma lista de alunos quando tem sucesso")
    void listAll_ReturnsListOfAlunos_WhenSuccessful(){
        String expectedNome = AlunoCreator.createrValidAluno().getNome();
        Page<Aluno> alunoPage = alunoController.list(null).getBody();

        Assertions.assertThat(alunoPage).isNotNull();

        Assertions.assertThat(alunoPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(alunoPage.toList().get(0).getNome()).isEqualTo(expectedNome);
    }


    @Test
    @DisplayName("Save, retorna um Aluno quando tem sucesso")
    void save_ReturnsAluno_WhenSuccessful() {
        int expectedId = AlunoCreator.createrValidAluno().getId();

        Aluno aluno = alunoController.save(AlunoPostRequestBodyCreator.createAlunoPostRequestBody()).getBody();

        Assertions.assertThat(aluno).isNotNull().isEqualTo(AlunoCreator.createrValidAluno());
    }

    @Test
    @DisplayName("Replace, atualiza um aluno quando tem sucesso")
    void replace_UpdateAluno_WhenSuccessful(){
        Assertions.assertThatCode(() -> alunoController.replace(AlunoPutRequestBodyCreator.createAlunoPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = alunoController.replace(AlunoPutRequestBodyCreator.createAlunoPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Delete, remove um aluno quando tem sucesso")
    void delete_RemovesAluno_WhenSuccessful(){
        Assertions.assertThatCode(() -> alunoController.delete(1)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = alunoController.delete(1);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}