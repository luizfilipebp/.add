package br.add.desafio.controller;

import br.add.desafio.model.Escola;
import br.add.desafio.requests.Escola.EscolaPostRequestBody;
import br.add.desafio.requests.Escola.EscolaPutRequestBody;
import br.add.desafio.service.EscolaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/escola")
@Log4j2
@RequiredArgsConstructor

public class EscolaController {
    private final EscolaService escolaService;

    @Operation(summary = "Lista todas as escolas paginadas", tags = {"Escola"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realiza com sucesso"),
    })
    @GetMapping
    public ResponseEntity<Page<Escola>> list(@ParameterObject  Pageable pageable){
        return ResponseEntity.ok(escolaService.listAll(pageable));
    }

    @Operation(summary = "Registra uma nova escola", tags = {"Escola"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realiza com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request, verifique a mensagem"),
    })
    @PostMapping
    public ResponseEntity<Escola> save(@RequestBody @Valid EscolaPostRequestBody escolaPostRequestBody){
        return new ResponseEntity<>(escolaService.save(escolaPostRequestBody), HttpStatus.CREATED);
    }

    @Operation(summary = "Altera uma escola", tags = {"Escola"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realiza com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request, verifique a mensagem"),
    })
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid EscolaPutRequestBody escolaPutRequestBody){
        escolaService.replace(escolaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deleta uma escola", tags = {"Escola"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realiza com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request, verifique a mensagem"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        escolaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
