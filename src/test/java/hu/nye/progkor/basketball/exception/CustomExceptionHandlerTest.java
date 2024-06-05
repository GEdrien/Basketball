package hu.nye.progkor.basketball.exception;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.configuration.IMockitoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomExceptionHandlerTest {

    @Mock
    private GlobalException globalException;
    @Mock
    private MethodArgumentNotValidException exception;
    @Mock
    private BindingResult bindingResult;
    private CustomExceptionHandler customExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customExceptionHandler = new CustomExceptionHandler();
    }

    @Test
    public void testGlobalException() {
        when(globalException.getMessage()).thenReturn("Custom error message");
        Map<String, String> expectedError = Map.of("error", "Custom error message");

        Map<String, String> actualError = customExceptionHandler.globalException(globalException);

        assertEquals(expectedError, actualError);
    }

    /*
    @Test
    public void testHandleValidateException() {
        FieldError fieldError1 = new FieldError("objectName", "field1", "Error message 1");
        FieldError fieldError2 = new FieldError("objectName", "field2", "Error message 2");
        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("field1", "Error message 1");
        expectedErrors.put("field2", "Error message 2");

        Map<String, String> actualErrors = new ExceptionHandlerTest().handleValidateException(exception);

        assertEquals(expectedErrors, actualErrors);
    }

    private class ExceptionHandlerTest {
        @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public Map<String, String> handleValidateException(MethodArgumentNotValidException exception) {
            Map<String, String> errors = new HashMap<>();
            exception.getBindingResult()
                    .getFieldErrors()
                    .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return errors;
        }
    }*/

}