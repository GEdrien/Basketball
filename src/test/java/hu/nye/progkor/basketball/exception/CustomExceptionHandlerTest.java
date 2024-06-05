package hu.nye.progkor.basketball.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.configuration.IMockitoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomExceptionHandlerTest {

    @Mock
    private GlobalException globalException;
    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, null);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError1 = new FieldError("objectName", "fieldName1", "Error message 1");
        FieldError fieldError2 = new FieldError("objectName", "fieldName2", "Error message 2");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        Map<String, String> result = customExceptionHandler.handleValidateException(exception);

        assertEquals("Error message 1", result.get("fieldName1"));
        assertEquals("Error message 2", result.get("fieldName2"));
    }*/

}