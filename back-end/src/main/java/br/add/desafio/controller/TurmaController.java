package br.add.desafio.controller;

import br.add.desafio.model.Turma;
import br.add.desafio.requests.Turma.TurmaPostRequestBody;
import br.add.desafio.requests.Turma.TurmaPutRequestBody;
import br.add.desafio.service.TurmaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turma")
@Log4j2
@RequiredArgsConstructor

public class TurmaController {
    private final TurmaService turmaService;

    @GetMapping
    public ResponseEntity<Page<Turma>> list(Pageable pageable){
        return ResponseEntity.ok(turmaService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Turma> save(@RequestBody TurmaPostRequestBody turmaPostRequestBody){
        return new ResponseEntity<>(turmaService.save(turmaPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody TurmaPutRequestBody turmaPutRequestBody){
        turmaService.replace(turmaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        turmaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
