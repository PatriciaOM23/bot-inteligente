package es.fplumara.dam.rebot.exceptions;

public class ConfigException extends RuntimeException {
    private Exception parentException;
    public ConfigException(String message) {
        super(message);
    }
    public ConfigException(String message,Exception e ){
        super(message);
        parentException = e;
    }
}
