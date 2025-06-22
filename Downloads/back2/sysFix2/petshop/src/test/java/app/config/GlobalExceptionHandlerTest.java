package app.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve tratar MethodArgumentNotValidException e retornar erros de campo")
    void handle01_MethodArgumentNotValidException() {
        BindException bindException = new BindException(new Object(), "target");
        bindException.addError(new FieldError("target", "campo1", "Mensagem de erro 1"));
        bindException.addError(new FieldError("target", "campo2", "Mensagem de erro 2"));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException);

        ResponseEntity<Map<String, String>> resposta = handler.handle01(ex);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals("Mensagem de erro 1", resposta.getBody().get("campo1"));
        assertEquals("Mensagem de erro 2", resposta.getBody().get("campo2"));
    }
  //TESTE DE INTEGRACAO
    @Test
    @DisplayName("Deve tratar Exception genérica e retornar mensagem de erro")
    void handle03_GeneralException() {
        Exception ex = new Exception("Erro genérico");
        ResponseEntity<String> resposta = handler.handle03(ex);

        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
        assertEquals("Erro genérico", resposta.getBody());
    }
}
