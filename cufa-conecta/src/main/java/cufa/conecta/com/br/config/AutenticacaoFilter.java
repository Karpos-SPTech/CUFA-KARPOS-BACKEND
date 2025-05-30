package cufa.conecta.com.br.config;

import cufa.conecta.com.br.resources.AutenticacaoRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class AutenticacaoFilter extends OncePerRequestFilter {
  private static final Logger LOGGER = LoggerFactory.getLogger(AutenticacaoFilter.class);

  private final AutenticacaoRepository autenticacao;
  private final GerenciadorTokenJwt jwtTokenManager;

  public AutenticacaoFilter(
      AutenticacaoRepository autenticacao, GerenciadorTokenJwt jwtTokenManager) {
    this.autenticacao = autenticacao;
    this.jwtTokenManager = jwtTokenManager;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String jwtToken = getTokenFromRequest(request);

    if (jwtToken != null) {
      try {
        String username = jwtTokenManager.getUsernameFromToken(jwtToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          addUsernameInContext(request, username, jwtToken);
        }
      } catch (ExpiredJwtException exception) {
        handleExpiredToken(exception, response);
        return;
      }
    }

    filterChain.doFilter(request, response);
  }

  private void addUsernameInContext(HttpServletRequest request, String username, String jwtToken) {
    UserDetails userDetails = autenticacao.loadUserByUsername(username);

    if (jwtTokenManager.validateToken(jwtToken, userDetails)) {
      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
  }

  // 🔄 Novo método que verifica tanto o Header quanto o Cookie
  private String getTokenFromRequest(HttpServletRequest request) {
    // Tenta primeiro no header Authorization
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      return header.substring(7);
    }

    // Tenta no cookie se não tiver no header
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if ("jwt".equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }

    return null;
  }

  private void handleExpiredToken(ExpiredJwtException exception, HttpServletResponse response) {
    LOGGER.info(
        "[FALHA AUTENTICAÇÃO] - Token expirado, usuário: {} - {}",
        exception.getClaims().getSubject(),
        exception.getMessage());
    LOGGER.trace("[FALHA AUTENTICAÇÃO] - stack trace: {}", exception);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  }
}
