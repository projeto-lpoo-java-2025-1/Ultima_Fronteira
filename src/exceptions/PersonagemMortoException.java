package exceptions;

public class PersonagemMortoException extends RuntimeException {
    public PersonagemMortoException() {
        super("Personagem morreu!");
    }
}
