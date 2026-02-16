package es.fplumara.dam.rebot.exceptions;

public class StoreException extends RuntimeException {
    private Exception parentException;
    public StoreException(String message) {
        super(message);
    }
    public StoreException(String message,Exception e ){
        super(message);
        parentException = e;
    }
}
