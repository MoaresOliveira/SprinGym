package io.github.moaresoliveira.springym.controller;

import io.github.moaresoliveira.springym.entity.Matricula;
import io.github.moaresoliveira.springym.entity.form.MatriculaForm;
import io.github.moaresoliveira.springym.service.impl.MatriculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaServiceImpl service;

    @PostMapping
    public ResponseEntity<Matricula> create(@Valid @RequestBody MatriculaForm form) {
        Matricula matricula = service.create(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
    }

    @GetMapping
    public ResponseEntity<List<Matricula>> getAll(@RequestParam(value = "bairro", required = false) String bairro) {
        List<Matricula> matriculas = service.getAll(bairro);
        return matriculas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(matriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> get(@PathVariable Long id) {
        Matricula matricula = service.get(id);
        return ResponseEntity.ok(matricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}

