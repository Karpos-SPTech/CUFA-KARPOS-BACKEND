package cufa.conecta.com.br.config;

import cufa.conecta.com.br.resources.user.AutenticacaoRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class AutenticacaoFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutenticacaoFilter.class);

    private final AutenticacaoRepository autenticacao;

    private final GerenciadorTokenJwt jwtTokenManager;

    public AutenticacaoFilter(
            AutenticacaoRepository autenticacao,
            GerenciadorTokenJwt jwtTokenManager
    ) {
        this.autenticacao = autenticacao;
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException
    {
        String requestTokenHeader = request.getHeader("Authorization");

        if (isBearerToken(requestTokenHeader)) {
            String jwtToken = extractToken(requestTokenHeader);
            String username;

            try {
                username = jwtTokenManager.getUsernameFromToken(jwtToken);
            } catch (ExpiredJwtException exception) {
                handleExpiredToken(exception, response);
                return;
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                addUsernameInContext(request, username, jwtToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void addUsernameInContext(HttpServletRequest request, String username, String jwtToken) {
        UserDetails userDetails = autenticacao.loadUserByUsername(username);

        if (jwtTokenManager.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private boolean isBearerToken(String requestTokenHeader) {
        return Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith("Bearer");
    }

    private String extractToken(String requestTokenHeader) {
        return requestTokenHeader.substring(7);
    }

    private void handleExpiredToken(ExpiredJwtException exception, HttpServletResponse response) {
        LOGGER.info("[FALHA AUTENTICAÇÃO] - Token expirado, usuário: {} - {}",
                exception.getClaims().getSubject(), exception.getMessage());
        LOGGER.trace("[FALHA AUTENTICAÇÃO] - stack trace: {}", exception);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
