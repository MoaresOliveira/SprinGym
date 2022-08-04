package io.github.moaresoliveira.springym.controller;

import io.github.moaresoliveira.springym.entity.Aluno;
import io.github.moaresoliveira.springym.entity.AvaliacaoFisica;
import io.github.moaresoliveira.springym.entity.form.AlunoForm;
import io.github.moaresoliveira.springym.entity.form.AlunoUpdateForm;
import io.github.moaresoliveira.springym.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl service;

    @PostMapping
    public ResponseEntity<Aluno> create(@Valid @RequestBody AlunoForm form) {
        Aluno aluno = service.create(form);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @GetMapping("/avaliacoes/{id}")
    public ResponseEntity<List<AvaliacaoFisica>> getAllAvaliacaoFisicaId(@PathVariable Long id) {
        List<AvaliacaoFisica> avaliacaoFisicas = service.getAllAvaliacaoFisicaId(id);
        return avaliacaoFisicas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(avaliacaoFisicas);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(@RequestParam(value = "dataDeNascimento", required = false)
                                                      String dataDeNacimento) {
        List<Aluno> alunos = service.getAll(dataDeNacimento);
        return alunos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable Long id) {
        Aluno aluno = service.getById(id);
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @Valid @RequestBody AlunoUpdateForm formUpdate) {
        Aluno aluno = service.update(id, formUpdate);
        return ResponseEntity.ok(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
