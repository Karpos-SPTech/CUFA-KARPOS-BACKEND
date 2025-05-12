package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.documentation.UsuarioControllerDoc;
import cufa.conecta.com.br.application.dto.request.LoginDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioResponseDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioTokenDto;
import cufa.conecta.com.br.application.dto.request.usuario.UsuarioRequestDto;
import cufa.conecta.com.br.domain.service.UsuarioService;
import cufa.conecta.com.br.model.UsuarioData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioControllerDoc {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) { this.service = service; }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario(@RequestBody @Valid UsuarioRequestDto userDto) {
            service.cadastrarUsuario(userDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioTokenDto login(@RequestBody LoginDto usuarioLoginDto) {
        return service.login(usuarioLoginDto);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        List<UsuarioResponseDto> usuariosEncontrados = service.listarTodos();

        if (usuariosEncontrados.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuariosEncontrados);
    }

    @PutMapping("/{id}")
    public void atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDto usuarioDto) {
        UsuarioData usuarioParaAtualizar = usuarioDto.toModel();
        usuarioParaAtualizar.setId(id);

        service.atualizarUsuario(usuarioParaAtualizar);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) { service.deletar(id); }
}
