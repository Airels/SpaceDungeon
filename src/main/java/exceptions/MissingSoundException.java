package exceptions;

public class MissingSoundException extends RuntimeException {
    public MissingSoundException(String msg) {
        super("Invalid sound: " + msg);
    }
}
