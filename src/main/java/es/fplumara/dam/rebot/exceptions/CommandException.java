package es.fplumara.dam.rebot.exceptions;

public class CommandException extends RuntimeException {
    private Exception parentException;
    public CommandException(String message) {
        super(message);
    }
    public CommandException(String message,Exception e ){
        super(message);
        parentException = e;
    }
}

