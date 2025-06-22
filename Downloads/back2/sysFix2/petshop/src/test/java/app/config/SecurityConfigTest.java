//package app.config;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.apache.catalina.filters.CorsFilter;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.mysql.cj.protocol.AuthenticationProvider;
//
//
//@SpringBootTest
//@Import(SecurityConfig.class)public class SecurityConfigTest {
//	
//	@Autowired
//    private SecurityFilterChain securityFilterChain;
//
//    @Autowired
//    private FilterRegistrationBean<CorsFilter> corsFilter;
//
//    // Mocks obrigatórios para subir o contexto Spring
//    @MockBean
//    private JwtAuthenticationFilter jwtAuthFilter;
//
//    @MockBean
//    private AuthenticationProvider authenticationProvider;
//
//    @Test
//    @DisplayName("Deve carregar SecurityFilterChain corretamente")
//    void testSecurityFilterChainIsNotNull() {
//        assertThat(securityFilterChain).isNotNull();
//    }
//
//    @Test
//    @DisplayName("Deve registrar o CorsFilter com a ordem correta")
//    void testCorsFilterRegistration() {
//        assertThat(corsFilter).isNotNull();
//        assertThat(corsFilter.getOrder()).isEqualTo(-102);
//    }
//
//}
