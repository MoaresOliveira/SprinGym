package io.github.moaresoliveira.springym.infra.exception;

public class MatriculaNotFoundException extends RuntimeException {

    public MatriculaNotFoundException(String message) {
        super(message);
    }

}
