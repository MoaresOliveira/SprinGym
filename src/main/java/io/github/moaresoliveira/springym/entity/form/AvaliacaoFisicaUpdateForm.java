package io.github.moaresoliveira.springym.entity.form;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AvaliacaoFisicaUpdateForm {

  @NotNull(message = "Preencha o campo corretamente.")
  @Positive(message = "${validatedValue}' precisa ser positivo.")
  @DecimalMin(value = "30", message = "'${validatedValue}' precisa ser no mínimo {value}.")
  private double peso;

  @NotNull(message = "Preencha o campo corretamente.")
  @Positive(message = "${validatedValue}' precisa ser positivo.")
  @DecimalMin(value = "140", message = "'${validatedValue}' precisa ser no mínimo {value}.")
  private double altura;
}
