package school.sptech.exceptions;

public class NotaInvalidaException extends RuntimeException{

    public NotaInvalidaException(){}

    public NotaInvalidaException(String message) {
        super(message);
    }
}
