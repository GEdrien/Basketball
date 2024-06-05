package hu.nye.progkor.basketball.exception;

/**
 * Exception that will occur in the program.
 */
public class GlobalException extends RuntimeException {
    public GlobalException(String message) {
        super(message);
    }
}