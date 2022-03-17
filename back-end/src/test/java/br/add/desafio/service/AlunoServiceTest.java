package br.add.desafio.service;

import br.add.desafio.exception.BadRequestException;
import br.add.desafio.model.Aluno;
import br.add.desafio.repository.AlunoRepository;
import br.add.desafio.requests.Aluno.AlunoPutRequestBody;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste para Aluno Service")
class AlunoServiceTest {
    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Aluno> alunoPage = new PageImpl<>(List.of(AlunoCreator.createrValidAluno()));

        BDDMockito.when(alunoRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(alunoPage);

        BDDMockito.when(alunoRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(AlunoCreator.createrValidAluno()));

        BDDMockito.when(alunoRepositoryMock.save(ArgumentMatchers.any(Aluno.class)))
                .thenReturn(AlunoCreator.createrValidAluno());

        BDDMockito.doNothing().when(alunoRepositoryMock).delete(ArgumentMatchers.any(Aluno.class));

    }
    @Test
    @DisplayName("findById, retorna Aluno quando tem sucesso")
    void findById_ReturnsAluno_WhenSuccessful() {
        int expectedId = AlunoCreator.createrValidAluno().getId();

        Aluno aluno = alunoService.findByIDOrThrowBadRequestException(1);

        Assertions.assertThat(aluno).isNotNull();
        Assertions.assertThat(aluno.getId()).isNotNull().isEqualTo(expectedId);
    }


    @Test
    @DisplayName("findByIDOrThrowBadRequestException, Lança uma bad request exception quando o aluno não é encontrado")
    void findByIDOrThrowBadRequestException_ThrowsBadRequestException_WhenAlunoIsNotFound() {
        BDDMockito.when(alunoRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> alunoService.findByIDOrThrowBadRequestException(1));

    }

    @Test
    @DisplayName("Save, retorna aluno quando tem sucesso")
    void save_ReturnsAluno_WhenSuccessful() {
        Aluno aluno = alunoService.save(AlunoPostRequestBodyCreator.createAlunoPostRequestBody());

        Assertions.assertThat(aluno).isNotNull().isEqualTo(AlunoCreator.createrValidAluno());
    }

    @Test
    @DisplayName("ListAll, retorna uma listas de alunos quando tem sucesso")
    void listAll_ReturnsListOfAluno_WhenSuccessful(){
        String expectedName = AlunoCreator.createrValidAluno().getNome();

        Page<Aluno> alunoPage = alunoService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(alunoPage).isNotNull();

        Assertions.assertThat(alunoPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(alunoPage.toList().get(0).getNome()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("Replace, atualiza um aluno quando tem sucesso")
    void replace_UpdateAluno_WhenSuccessful() {
        Assertions.assertThatCode(() -> alunoService.replace(AlunoPutRequestBodyCreator.createAlunoPutRequestBody()))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("Delete, remove um aluno quando tem sucesso")
    void delete_RemovesAluno_WhenSuccessful() {

        Assertions.assertThatCode(() -> alunoService.delete(1))
                .doesNotThrowAnyException();
    }
}