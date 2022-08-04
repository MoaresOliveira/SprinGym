package io.github.moaresoliveira.springym.controller;

import io.github.moaresoliveira.springym.entity.AvaliacaoFisica;
import io.github.moaresoliveira.springym.entity.form.AvaliacaoFisicaForm;
import io.github.moaresoliveira.springym.entity.form.AvaliacaoFisicaUpdateForm;
import io.github.moaresoliveira.springym.service.impl.AvaliacaoFisicaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaServiceImpl service;

    @PostMapping
    public ResponseEntity<AvaliacaoFisica> create(@Valid @RequestBody AvaliacaoFisicaForm form) {
        AvaliacaoFisica avaliacaoFisica = service.create(form);
        return ResponseEntity.ok(avaliacaoFisica);
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoFisica>> getAll() {
        List<AvaliacaoFisica> avaliacaoFisicas = service.getAll();
        return avaliacaoFisicas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(avaliacaoFisicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoFisica> getById(@PathVariable Long id) {
        AvaliacaoFisica avaliacaoFisica = service.get(id);
        return ResponseEntity.ok(avaliacaoFisica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoFisica> update(@PathVariable Long id,@Valid @RequestBody AvaliacaoFisicaUpdateForm form) {
        AvaliacaoFisica avaliacaoFisica = service.update(id, form);
        return ResponseEntity.ok(avaliacaoFisica);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AvaliacaoFisica> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
