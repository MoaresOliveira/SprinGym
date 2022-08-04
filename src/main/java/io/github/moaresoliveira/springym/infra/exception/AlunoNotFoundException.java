package io.github.moaresoliveira.springym.infra.exception;

public class AlunoNotFoundException extends RuntimeException {

    public AlunoNotFoundException(String message) {
        super(message);
    }

}
