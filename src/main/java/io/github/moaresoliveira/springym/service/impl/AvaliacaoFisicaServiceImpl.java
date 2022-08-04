package io.github.moaresoliveira.springym.service.impl;

import io.github.moaresoliveira.springym.entity.Aluno;
import io.github.moaresoliveira.springym.entity.AvaliacaoFisica;
import io.github.moaresoliveira.springym.entity.form.AvaliacaoFisicaForm;
import io.github.moaresoliveira.springym.entity.form.AvaliacaoFisicaUpdateForm;
import io.github.moaresoliveira.springym.infra.exception.AlunoNotFoundException;
import io.github.moaresoliveira.springym.infra.exception.AvaliacaoNotFoundException;
import io.github.moaresoliveira.springym.repository.AlunoRepository;
import io.github.moaresoliveira.springym.repository.AvaliacaoFisicaRepository;
import io.github.moaresoliveira.springym.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

  @Autowired
  private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
    AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
    Aluno aluno = alunoRepository.findById(form.getAlunoId())
        .orElseThrow(() -> new AlunoNotFoundException("Aluno " + form.getAlunoId() + " não encontrado"));

    avaliacaoFisica.setAluno(aluno);
    avaliacaoFisica.setPeso(form.getPeso());
    avaliacaoFisica.setAltura(form.getAltura());

    return avaliacaoFisicaRepository.save(avaliacaoFisica);
  }

  @Override
  public AvaliacaoFisica get(Long id) {
    Optional<AvaliacaoFisica> fisicaOptional = avaliacaoFisicaRepository.findById(id);
    if(fisicaOptional.isPresent()) {
      return fisicaOptional.get();
    }
    throw new AvaliacaoNotFoundException("Avaliação " + id + " não encontrada");
  }

  @Override
  public List<AvaliacaoFisica> getAll() {

    return avaliacaoFisicaRepository.findAll();
  }

  @Override
  public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
    Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
    if(avaliacaoFisica.isPresent()) {
      AvaliacaoFisica avaliacaoFisicaUpdate = avaliacaoFisica.get();
      avaliacaoFisicaUpdate.update(formUpdate);
      return avaliacaoFisicaRepository.save(avaliacaoFisicaUpdate);
    }
    throw new AvaliacaoNotFoundException("Avaliação " + id + " não encontrada");
  }

  @Override
  public void delete(Long id) {
    Optional<AvaliacaoFisica> avaliacaoFisica = avaliacaoFisicaRepository.findById(id);
    if(avaliacaoFisica.isPresent()) {
      avaliacaoFisicaRepository.delete(avaliacaoFisica.get());
    } else {
      throw new AvaliacaoNotFoundException("Avaliação " + id + " não encontrada");
    }
  }
}
