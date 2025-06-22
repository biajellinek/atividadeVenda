package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import app.auth.Login;
import app.auth.LoginRepository;
import app.auth.LoginService;
import app.auth.Usuario;
import app.config.JwtServiceGenerator;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginService loginService;

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private JwtServiceGenerator jwtServiceGenerator;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Login com sucesso retorna token JWT")
    void logarComSucesso() {
        Login login = new Login();
        login.setUsername("admin");
        login.setPassword("password123");

        Usuario usuario = new Usuario();
        usuario.setUsername("admin");

        String tokenEsperado = "Bearer token123";

        when(loginRepository.findByUsername("admin")).thenReturn(Optional.of(usuario));
        when(jwtServiceGenerator.generateToken(usuario)).thenReturn(tokenEsperado);

        String tokenGerado = loginService.logar(login);

        // Verifica se o método authenticate foi chamado com qualquer UsernamePasswordAuthenticationToken (igual para testar)
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        assertNotNull(tokenGerado);
        assertEquals(tokenEsperado, tokenGerado);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Login com credenciais inválidas lança exceção")
    void logarComErro() {
        Login login = new Login();
        login.setUsername("invalido");
        login.setPassword("senhaErrada");

        when(loginRepository.findByUsername("invalido")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            loginService.logar(login);
        });

        assertEquals("No value present", exception.getMessage());
    }
}
