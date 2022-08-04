package io.github.moaresoliveira.springym.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.moaresoliveira.springym.entity.form.AlunoUpdateForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_alunos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aluno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(unique = true)
  private String cpf;

  private String bairro;

  private LocalDate dataDeNascimento;

  @OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
  @JsonIgnore
  private List<AvaliacaoFisica> avaliacoes = new ArrayList<>();

  public Aluno(String nome, String cpf, String bairro, LocalDate dataDeNascimento) {
    this.nome = nome;
    this.cpf = cpf;
    this.bairro = bairro;
    this.dataDeNascimento = dataDeNascimento;
  }

  public void update(AlunoUpdateForm form) {
    this.nome = form.getNome();
    this.bairro = form.getBairro();
    this.dataDeNascimento = form.getDataDeNascimento();
  }
}
