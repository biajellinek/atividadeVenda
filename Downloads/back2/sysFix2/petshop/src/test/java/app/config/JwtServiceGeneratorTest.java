package app.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import app.auth.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtServiceGeneratorTest {

    @InjectMocks
    private JwtServiceGenerator jwtService;

    private Usuario usuario;
    private String token;

    @BeforeEach
    void setUp() {
        jwtService = new JwtServiceGenerator();
        usuario = new Usuario();
        usuario.setUsername("testuser");
        usuario.setRole("admin");
        usuario.setId(1L);

        token = jwtService.generateToken(usuario);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve gerar token JWT válido")
    void testGenerateToken() {
        assertNotNull(token);
        assertTrue(token.startsWith("eyJ"));
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve extrair username do token")
    void testExtractUsername() {
        String username = jwtService.extractUsername(token);
        assertEquals("testuser", username);
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve validar token válido para o usuário")
    void testIsTokenValid() {
        UserDetails userDetails = User.builder()
                .username("testuser")
                .password("password")
                .roles("admin")
                .build();

        assertTrue(jwtService.isTokenValid(token, userDetails));
    }

    @Test//TESTE DE INTEGRACAO
    @DisplayName("Deve verificar se token não está expirado")
    void testIsTokenExpired() {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(JwtServiceGenerator.SECRET_KEY)))
                .build()
                .parseClaimsJws(token)
                .getBody();

        Date expiration = claims.getExpiration();
        assertFalse(expiration.before(new Date()));
    }

}
