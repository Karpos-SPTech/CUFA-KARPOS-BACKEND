package cufa.conecta.com.br.application.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import cufa.conecta.com.br.config.GerenciadorTokenJwt;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginGoogleController {

    private final UsuarioDao userRepository;
    private final GerenciadorTokenJwt jwtService;

    private static final String CLIENT_ID = "296975099929-nnk3hihf30cdoh9qfveb1map9qt1h6c6.apps.googleusercontent.com";

    public LoginGoogleController(UsuarioDao userRepository, GerenciadorTokenJwt jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> body) {
        String idTokenString = body.get("idToken");

        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    new GsonFactory())
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);

            if (idToken != null) {
                Payload payload = idToken.getPayload();

                String email = payload.getEmail();
                String name = (String) payload.get("name");
                String googleId = payload.getSubject();

                Optional<UsuarioEntity> optionalUser = userRepository.findByEmail(email);
                UsuarioEntity user;

                if (optionalUser.isPresent()) {
                    user = optionalUser.get();

                } else {
                    user = new UsuarioEntity();
                    user.setNome(name);
                    user.setEmail(email);
                    user.setSenha(googleId);
                    userRepository.save(user);
                }

                String jwt = jwtService.generateTokenFromUser(user);

                return ResponseEntity.ok(Map.of("token", jwt));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Google inválido");
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na validação do token Google");
        }
    }
}

