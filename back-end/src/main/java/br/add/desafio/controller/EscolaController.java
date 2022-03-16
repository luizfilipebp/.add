package br.add.desafio.controller;

import br.add.desafio.model.Escola;
import br.add.desafio.requests.Escola.EscolaPostRequestBody;
import br.add.desafio.requests.Escola.EscolaPutRequestBody;
import br.add.desafio.service.EscolaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/escola")
@Log4j2
@RequiredArgsConstructor

public class EscolaController {
    private final EscolaService escolaService;

    @GetMapping
    public ResponseEntity<Page<Escola>> list(Pageable pageable){
        return ResponseEntity.ok(escolaService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Escola> save(@RequestBody EscolaPostRequestBody escolaPostRequestBody){
        return new ResponseEntity<>(escolaService.save(escolaPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody EscolaPutRequestBody escolaPutRequestBody){
        escolaService.replace(escolaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        escolaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
