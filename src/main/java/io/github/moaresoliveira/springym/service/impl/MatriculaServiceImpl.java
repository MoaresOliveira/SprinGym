package io.github.moaresoliveira.springym.service.impl;

import io.github.moaresoliveira.springym.entity.Aluno;
import io.github.moaresoliveira.springym.entity.Matricula;
import io.github.moaresoliveira.springym.entity.form.MatriculaForm;
import io.github.moaresoliveira.springym.infra.exception.AlunoNotFoundException;
import io.github.moaresoliveira.springym.infra.exception.MatriculaNotFoundException;
import io.github.moaresoliveira.springym.repository.AlunoRepository;
import io.github.moaresoliveira.springym.repository.MatriculaRepository;
import io.github.moaresoliveira.springym.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

  @Autowired
  private MatriculaRepository matriculaRepository;

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public Matricula create(MatriculaForm form) {
    Matricula matricula = new Matricula();
    Optional<Aluno> optionalAluno = alunoRepository.findById(form.getAlunoId());
    if(optionalAluno.isPresent()) {
      matricula.setAluno(optionalAluno.get());
      return matriculaRepository.save(matricula);
    }
    throw new AlunoNotFoundException("Aluno " + form.getAlunoId() + " não encontrado.");
  }

  @Override
  public Matricula get(Long id) {
    Optional<Matricula> optionalMatricula = matriculaRepository.findById(id);
    return optionalMatricula.orElseThrow(() -> new MatriculaNotFoundException("Matricula " + id + " não encontrada."));
  }

  @Override
  public List<Matricula> getAll(String bairro) {

    if(bairro == null){
      return matriculaRepository.findAll();
    }else{
      return matriculaRepository.findAlunosMatriculadosBairro(bairro);
    }

  }

  @Override
  public void delete(Long id) {
    Optional<Matricula> matriculaOptional = matriculaRepository.findById(id);
    if(matriculaOptional.isPresent()) {
      matriculaRepository.delete(matriculaOptional.get());
    } else {
      throw new MatriculaNotFoundException("Matricula " + id + " não encontrada.");
    }
  }



}
