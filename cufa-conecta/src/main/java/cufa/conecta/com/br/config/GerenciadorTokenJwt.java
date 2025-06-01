package cufa.conecta.com.br.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class GerenciadorTokenJwt {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.validity}")
  private Long jwtTokenValidity;

  private SecretKey secretKey;

  @PostConstruct
  public void init() {
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(Authentication authentication) {
    return generateToken(authentication, null);
  }

  public String generateToken(Authentication authentication, Long idEmpresa) {
    Map<String, Object> claims = new HashMap<>();
    if (idEmpresa != null) {
      claims.put("idEmpresa", idEmpresa);
    }

    return Jwts.builder()
        .setClaims(claims) // adiciona as claims customizadas
        .setSubject(authentication.getName()) // subject padrão (ex: username)
        .signWith(secretKey)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity * 1000))
        .compact();
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  // **Novo método para extrair idEmpresa da claim customizada**
  public Long getIdEmpresaFromToken(String token) {
    Claims claims = getAllClaimsFromToken(token);
    Integer idEmpresa = claims.get("idEmpresa", Integer.class);
    return idEmpresa != null ? idEmpresa.longValue() : null;
  }

  private Claims getAllClaimsFromToken(String token) {
    try {
      return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
      throw new RuntimeException("Token expirado", e);
    } catch (JwtException e) {
      throw new RuntimeException("Token inválido", e);
    }
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }
}
