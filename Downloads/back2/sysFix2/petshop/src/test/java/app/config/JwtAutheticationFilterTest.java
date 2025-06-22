//package app.config;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import org.springframework.mock.web.MockHttpServletRequest;
//
//
//
//import java.util.Collections;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import io.jsonwebtoken.io.IOException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//
//@ExtendWith(MockitoExtension.class)
//public class JwtAutheticationFilterTest {
//
//	@InjectMocks
//    private JwtAuthenticationFilter filter;
//
//    @Mock
//    private JwtServiceGenerator jwtService;
//
//    @Mock
//    private UserDetailsService userDetailsService;
//
//    @Mock
//    private FilterChain filterChain;
//
//    private MockHttpServletRequest request;
//    private MockHttpServletResponse response;
//
//    @BeforeEach
//    void setup() {
//        SecurityContextHolder.clearContext();
//        request = new MockHttpServletRequest();
//        response = new MockHttpServletResponse();
//    }
//
//    @Test
//    void shouldSetAuthenticationForValidToken() throws ServletException, IOException {
//        String token = "valid.token.here";
//        String email = "user@example.com";
//        request.addHeader("Authorization", "Bearer " + token);
//
//        UserDetails userDetails = new User(email, "password", Collections.emptyList());
//
//        when(jwtService.extractUsername(token)).thenReturn(email);
//        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);
//        when(jwtService.isTokenValid(token, userDetails)).thenReturn(true);
//
//        filter.doFilterInternal(request, response, filterChain);
//
//        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
//        assertEquals(email, SecurityContextHolder.getContext().getAuthentication().getName());
//
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//
//    @Test
//    void shouldNotSetAuthenticationIfTokenInvalid() throws ServletException, IOException {
//        String token = "invalid.token";
//        request.addHeader("Authorization", "Bearer " + token);
//
//        String email = "user@example.com";
//        UserDetails userDetails = new User(email, "password", Collections.emptyList());
//
//        when(jwtService.extractUsername(token)).thenReturn(email);
//        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);
//        when(jwtService.isTokenValid(token, userDetails)).thenReturn(false);
//
//        filter.doFilterInternal(request, response, filterChain);
//
//        assertNull(SecurityContextHolder.getContext().getAuthentication());
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//
//    @Test
//    void shouldSkipFilterIfNoAuthorizationHeader() throws ServletException, IOException {
//        filter.doFilterInternal(request, response, filterChain);
//
//        assertNull(SecurityContextHolder.getContext().getAuthentication());
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//
//    @Test
//    void shouldSkipFilterIfAuthorizationHeaderDoesNotStartWithBearer() throws ServletException, IOException {
//        request.addHeader("Authorization", "Basic sometoken");
//
//        filter.doFilterInternal(request, response, filterChain);
//
//        assertNull(SecurityContextHolder.getContext().getAuthentication());
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//}
//
