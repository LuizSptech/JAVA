package school.sptech.exceptions;

public class ItemNaoEncontradoException extends RuntimeException{
    public ItemNaoEncontradoException() {
    }

    public ItemNaoEncontradoException(String message) {
        super(message);
    }
}