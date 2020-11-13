package exceptions;

public class MissingAssetException extends RuntimeException {

    public MissingAssetException() {
        super("Unable to resolve asset");
    }

    public MissingAssetException(String msg) {
        super(msg);
    }
}
