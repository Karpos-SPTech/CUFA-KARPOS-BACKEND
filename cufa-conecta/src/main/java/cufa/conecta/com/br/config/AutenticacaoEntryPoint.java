package cufa.conecta.com.br.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AutenticacaoEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    if (authException.getClass().equals(BadCredentialsException.class)
        || authException.getClass().equals(InsufficientAuthenticationException.class)) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    } else {
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
  }
}
