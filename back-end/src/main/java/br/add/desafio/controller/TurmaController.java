package br.add.desafio.controller;

import br.add.desafio.model.Turma;
import br.add.desafio.requests.Turma.TurmaPostRequestBody;
import br.add.desafio.requests.Turma.TurmaPutRequestBody;
import br.add.desafio.service.TurmaService;
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
@RequestMapping("/turma")
@Log4j2
@RequiredArgsConstructor

public class TurmaController {
    private final TurmaService turmaService;

    @Operation(summary = "Lista todas as turmas paginadas", tags = {"Turma"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realiza com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request, verifique a mensagem"),
    })
    @GetMapping
    public ResponseEntity<Page<Turma>> list(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(turmaService.listAll(pageable));
    }

    @Operation(summary = "Registra uma nova turma", tags = {"Turma"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realiza com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request, verifique a mensagem"),
    })
    @PostMapping
    public ResponseEntity<Turma> save(@RequestBody @Valid TurmaPostRequestBody turmaPostRequestBody){
        return new ResponseEntity<>(turmaService.save(turmaPostRequestBody), HttpStatus.CREATED);
    }

    @Operation(summary = "Altera uma turma", tags = {"Turma"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realiza com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request, verifique a mensagem"),
    })
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid TurmaPutRequestBody turmaPutRequestBody){
        turmaService.replace(turmaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deleta uma turma", tags = {"Turma"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realiza com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request, verifique a mensagem"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        turmaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
