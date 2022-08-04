package io.github.moaresoliveira.springym.controller;

import io.github.moaresoliveira.springym.infra.exception.AlunoNotFoundException;
import io.github.moaresoliveira.springym.infra.exception.AvaliacaoNotFoundException;
import io.github.moaresoliveira.springym.infra.exception.MatriculaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({AlunoNotFoundException.class, MatriculaNotFoundException.class, AvaliacaoNotFoundException.class})
    public ResponseEntity<String> handleNotFoundExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String fieldName = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getField();
        String fieldMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo " + fieldName + " => " + fieldMessage);
    }
}
