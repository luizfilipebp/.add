package br.add.desafio.controller;

import br.add.desafio.model.Aluno;
import br.add.desafio.requests.Aluno.AlunoPostRequestBody;
import br.add.desafio.requests.Aluno.AlunoPutRequestBody;
import br.add.desafio.service.AlunoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
@Log4j2
@RequiredArgsConstructor

public class AlunoController {
    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<Page<Aluno>> list(Pageable pageable){
        return ResponseEntity.ok(alunoService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody AlunoPostRequestBody alunoPostRequestBody){
        return new ResponseEntity<>(alunoService.save(alunoPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AlunoPutRequestBody alunoPutRequestBody){
        alunoService.replace(alunoPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        alunoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
