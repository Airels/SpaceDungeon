package exceptions;

public class MissingAssetException extends Exception {

    public MissingAssetException() {
        super("Unable to resolve asset");
    }

    public MissingAssetException(String msg) {
        super(msg);
    }
}
