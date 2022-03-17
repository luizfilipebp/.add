package br.add.desafio.service;

import br.add.desafio.exception.BadRequestException;
import br.add.desafio.model.Escola;
import br.add.desafio.repository.EscolaRepository;
import br.add.desafio.util.escola.EscolaCreator;
import br.add.desafio.util.escola.EscolaPostRequestBodyCreator;
import br.add.desafio.util.escola.EscolaPutRequestBodyCreator;
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
@DisplayName("Teste para Escola Service")
class EscolaServiceTest {
    @InjectMocks
    private EscolaService escolaService;

    @Mock
    private EscolaRepository escolaRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Escola> escolaPage = new PageImpl<>(List.of(EscolaCreator.createValidEscola()));

        BDDMockito.when(escolaRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(escolaPage);

        BDDMockito.when(escolaRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.of(EscolaCreator.createValidEscola()));

        BDDMockito.when(escolaRepositoryMock.save(ArgumentMatchers.any(Escola.class)))
                .thenReturn(EscolaCreator.createValidEscola());

        BDDMockito.doNothing().when(escolaRepositoryMock).delete(ArgumentMatchers.any(Escola.class));

    }
    @Test
    @DisplayName("findById, retorna Escola quando tem sucesso")
    void findById_ReturnsEscola_WhenSuccessful() {
        int expectedId = EscolaCreator.createValidEscola().getId();

        Escola escola = escolaService.findByIDOrThrowBadRequestException(1);

        Assertions.assertThat(escola).isNotNull();
        Assertions.assertThat(escola.getId()).isNotNull().isEqualTo(expectedId);
    }


    @Test
    @DisplayName("findByIDOrThrowBadRequestException, Lança uma bad request exception quando o escola não é encontrado")
    void findByIDOrThrowBadRequestException_ThrowsBadRequestException_WhenEscolaIsNotFound() {
        BDDMockito.when(escolaRepositoryMock.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> escolaService.findByIDOrThrowBadRequestException(1));

    }

    @Test
    @DisplayName("Save, retorna escola quando tem sucesso")
    void save_ReturnsEscola_WhenSuccessful() {
        Escola escola = escolaService.save(EscolaPostRequestBodyCreator.createEscolaPostRequestBody());

        Assertions.assertThat(escola).isNotNull().isEqualTo(EscolaCreator.createValidEscola());
    }

    @Test
    @DisplayName("ListAll, retorna uma listas de escolas quando tem sucesso")
    void listAll_ReturnsListOfEscola_WhenSuccessful(){
        String expectedName = EscolaCreator.createValidEscola().getNome();

        Page<Escola> escolaPage = escolaService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(escolaPage).isNotNull();

        Assertions.assertThat(escolaPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(escolaPage.toList().get(0).getNome()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("Replace, atualiza um escola quando tem sucesso")
    void replace_UpdateEscola_WhenSuccessful() {
        Assertions.assertThatCode(() -> escolaService.replace(EscolaPutRequestBodyCreator.createEscolaPutRequestBody()))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("Delete, remove um escola quando tem sucesso")
    void delete_RemovesEscola_WhenSuccessful() {

        Assertions.assertThatCode(() -> escolaService.delete(1))
                .doesNotThrowAnyException();
    }
}