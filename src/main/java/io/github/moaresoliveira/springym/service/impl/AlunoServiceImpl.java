package io.github.moaresoliveira.springym.service.impl;

import io.github.moaresoliveira.springym.entity.Aluno;
import io.github.moaresoliveira.springym.entity.AvaliacaoFisica;
import io.github.moaresoliveira.springym.entity.form.AlunoForm;
import io.github.moaresoliveira.springym.entity.form.AlunoUpdateForm;
import io.github.moaresoliveira.springym.infra.exception.AlunoNotFoundException;
import io.github.moaresoliveira.springym.infra.utils.JavaTimeUtils;
import io.github.moaresoliveira.springym.repository.AlunoRepository;
import io.github.moaresoliveira.springym.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements IAlunoService {

  @Autowired
  private AlunoRepository repository;

  @Override
  public Aluno create(AlunoForm form) {
    Aluno aluno = form.toAluno();

    return repository.save(aluno);
  }

  @Override
  public Aluno getById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new AlunoNotFoundException("Aluno " + id + " Não encontrado"));
  }

  @Override
  public List<Aluno> getAll(String dataDeNascimento) {

    if(dataDeNascimento == null) {
      return repository.findAll();
    } else {
      LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
      return repository.findByDataDeNascimento(localDate);
    }

  }

  @Override
  public Aluno update(Long id, AlunoUpdateForm formUpdate) {
    Optional<Aluno> alunoOptional = repository.findById(id);
    if(alunoOptional.isPresent()) {
      Aluno aluno = alunoOptional.get();
      aluno.update(formUpdate);
      return repository.save(aluno);
    } else {
      throw new AlunoNotFoundException("Aluno " + id + " Não encontrado");
    }
  }

  @Override
  public void delete(Long id) {
    Optional<Aluno> alunoOptional = repository.findById(id);
    if(alunoOptional.isPresent()) {
      repository.delete(alunoOptional.get());
    } else {
      throw new AlunoNotFoundException("Aluno " + id + " Não encontrado");
    }
  }

  @Override
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {

    Aluno aluno = repository.findById(id).get();

    return aluno.getAvaliacoes();

  }

}
