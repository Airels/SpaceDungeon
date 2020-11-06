package exceptions;

public class RoomNotGeneratedException extends RuntimeException {
    public RoomNotGeneratedException(String msg) {
        super(msg);
    }
}
