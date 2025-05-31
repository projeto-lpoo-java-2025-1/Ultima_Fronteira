package exceptions;

public class EnergiaInsuficienteException extends RuntimeException {
    public EnergiaInsuficienteException() {
        super("Energia insuficiente para realizar a ação!");
    }
}
