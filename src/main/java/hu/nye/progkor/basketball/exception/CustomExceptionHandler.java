package hu.nye.progkor.basketball.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Custom Exception handling, put readable error messages to the user.
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handle Entity validation exception, and returns error messages.
     *
     * @param exception Entity validation exception.
     * @return Error message for the user.
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidateException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    /**
     * Handle Global exception, and returns error message corresponding to it.
     *
     * @param exception Global exception that handle most of the program exception.
     * @return Error message for the user.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(GlobalException.class)
    public Map<String, String> globalException(GlobalException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());
        return error;
    }

}