/*package app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.auth.Login;
import app.auth.LoginController;
import app.auth.LoginService;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private LoginService loginService;

    @Test
    @DisplayName("Deve autenticar usuário com sucesso e retornar token")
    void logarComSucesso() {
        Login login = new Login();
        login.setUsername("admin");
        login.setPassword("password123");

        String tokenEsperado = "Bearer token123";
        when(loginService.logar(login)).thenReturn(tokenEsperado);

        ResponseEntity<String> resposta = loginController.logar(login);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        assertEquals(tokenEsperado, resposta.getBody());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar autenticar com credenciais inválidas")
    void logarComErro() {
        Login login = new Login();
        login.setUsername("admin");
        login.setPassword("senhaIncorreta");

        when(loginService.logar(login)).thenThrow(new RuntimeException("Credenciais inválidas"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            loginController.logar(login);
        });

        assertEquals("Credenciais inválidas", exception.getMessage());
    }

    @Test
    @DisplayName("Deve acessar endpoint admin com sucesso")
    void acessarAdminComSucesso() {
        String resposta = loginController.adminEndpoint();
        assertNotNull(resposta);
        assertEquals("benvindo, adm", resposta);
    }

    @Test
    @DisplayName("Deve acessar endpoint user com sucesso")
    void acessarUserComSucesso() {
        String resposta = loginController.userEndpoint();
        assertNotNull(resposta);
        assertEquals("benvindo, user", resposta);
    }
}*/
