package exceptions;

public class ReceitaInvalidaException extends RuntimeException {
    public ReceitaInvalidaException(String message) {
        super(message);
    }
}
