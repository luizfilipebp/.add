package br.add.desafio.controller;

import br.add.desafio.model.Escola;
import br.add.desafio.requests.Escola.EscolaPostRequestBody;
import br.add.desafio.service.EscolaService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes para Escola Controller")
class EscolaControllerTest {
    @InjectMocks
    private EscolaController escolaController;

    @Mock
    private EscolaService escolaServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Escola> escolasPage = new PageImpl<>(List.of(EscolaCreator.createValidEscola()));
        BDDMockito.when(escolaServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(escolasPage);

        BDDMockito.when(escolaServiceMock.save(ArgumentMatchers.any(EscolaPostRequestBody.class)))
                .thenReturn(EscolaCreator.createValidEscola());

        BDDMockito.doNothing().when(escolaServiceMock).delete(ArgumentMatchers.anyInt());
    }

    @Test
    @DisplayName("List, retorna uma lista de escolas dentro de um page object quando tem sucesso")
    void list_ReturnsListOfEscolaInsidePageObject_WhenSuccessful() {
        String expectedNome = EscolaCreator.createValidEscola().getNome();
        Page<Escola> escolaPage = escolaController.list(null).getBody();

        Assertions.assertThat(escolaPage).isNotNull();

        Assertions.assertThat(escolaPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(escolaPage.toList().get(0).getNome()).isEqualTo(expectedNome);
    }

    @DisplayName("ListAll, retorna uma lista de escolas quando tem sucesso")
    void listAll_ReturnsListOfEscolas_WhenSuccessful(){
        String expectedNome = EscolaCreator.createValidEscola().getNome();
        Page<Escola> escolaPage = escolaController.list(null).getBody();

        Assertions.assertThat(escolaPage).isNotNull();

        Assertions.assertThat(escolaPage.toList())
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(escolaPage.toList().get(0).getNome()).isEqualTo(expectedNome);
    }


    @Test
    @DisplayName("Save, retorna um Escola quando tem sucesso")
    void save_ReturnsEscola_WhenSuccessful() {
        int expectedId = EscolaCreator.createValidEscola().getId();

        Escola escola = escolaController.save(EscolaPostRequestBodyCreator.createEscolaPostRequestBody()).getBody();

        Assertions.assertThat(escola).isNotNull().isEqualTo(EscolaCreator.createValidEscola());
    }

    @Test
    @DisplayName("Replace, atualiza um escola quando tem sucesso")
    void replace_UpdateEscola_WhenSuccessful(){
        Assertions.assertThatCode(() -> escolaController.replace(EscolaPutRequestBodyCreator.createEscolaPutRequestBody()))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = escolaController.replace(EscolaPutRequestBodyCreator.createEscolaPutRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("Delete, remove um escola quando tem sucesso")
    void delete_RemovesEscola_WhenSuccessful(){
        Assertions.assertThatCode(() -> escolaController.delete(1)).doesNotThrowAnyException();

        ResponseEntity<Void> entity = escolaController.delete(1);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}