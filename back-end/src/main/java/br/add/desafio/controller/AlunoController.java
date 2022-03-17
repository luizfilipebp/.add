package br.add.desafio.controller;

import br.add.desafio.model.Aluno;
import br.add.desafio.requests.Aluno.AlunoPostRequestBody;
import br.add.desafio.requests.Aluno.AlunoPutRequestBody;
import br.add.desafio.service.AlunoService;
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
@RequestMapping("/aluno")
@Log4j2
@RequiredArgsConstructor

public class AlunoController {
    private final AlunoService alunoService;

    @Operation(summary = "Lista todos os alunos paginados", tags = {"Aluno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realiza com sucesso"),
    })
    @GetMapping
    public ResponseEntity<Page<Aluno>> list(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(alunoService.listAll(pageable));
    }

    @Operation(summary = "Registra um novo aluno", tags = {"Aluno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realiza com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request, verifique a mensagem"),
    })
    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody @Valid AlunoPostRequestBody alunoPostRequestBody){
        return new ResponseEntity<>(alunoService.save(alunoPostRequestBody), HttpStatus.CREATED);
    }

    @Operation(summary = "Altera um aluno", tags = {"Aluno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realiza com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request, verifique a mensagem"),
    })
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid AlunoPutRequestBody alunoPutRequestBody){
        alunoService.replace(alunoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Deleta um aluno específico", tags = {"Aluno"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realiza com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request, verifique a mensagem"),
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
