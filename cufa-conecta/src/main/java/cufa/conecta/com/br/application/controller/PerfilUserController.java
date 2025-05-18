package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.dto.request.usuario.UsuarioPerfilRequestDto;
import cufa.conecta.com.br.domain.service.PerfilUserService;
import cufa.conecta.com.br.model.UsuarioData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilUserController {
    private final PerfilUserService service;

    public PerfilUserController(PerfilUserService service) {
        this.service = service;
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarPerfilUsuario(@RequestBody @Valid UsuarioPerfilRequestDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        service.criarPerfilUsuario(userDto, email);
    }
}
