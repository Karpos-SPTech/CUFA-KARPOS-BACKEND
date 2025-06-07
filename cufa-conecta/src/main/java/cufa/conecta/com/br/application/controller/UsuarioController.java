package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.documentation.UsuarioControllerDoc;
import cufa.conecta.com.br.application.dto.request.LoginDto;
import cufa.conecta.com.br.application.dto.request.usuario.UsuarioRequestDto;
import cufa.conecta.com.br.application.dto.request.usuario.UsuarioUpdateRequestDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioResponseDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioTokenDto;
import cufa.conecta.com.br.domain.service.usuario.UsuarioService;
import cufa.conecta.com.br.model.UsuarioData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioControllerDoc {
  private final UsuarioService service;
  private final CurriculoController curriculoController;

  public UsuarioController(UsuarioService service, CurriculoController curriculoController) {
    this.service = service;
    this.curriculoController = curriculoController;
  }

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

    if (usuariosEncontrados.isEmpty()) {
      return ResponseEntity.status(204).build();
    }
    return ResponseEntity.status(200).body(usuariosEncontrados);
  }

  @PutMapping("/{id}")
  public void atualizarUsuario(
      @PathVariable Long id, @RequestBody UsuarioUpdateRequestDto usuarioDto) {
    UsuarioData usuarioParaAtualizar = usuarioDto.toModel();
    usuarioParaAtualizar.setId(id);

    service.atualizarUsuario(usuarioParaAtualizar);
  }

  @GetMapping("/{id}")
  @SecurityRequirement(name = "Bearer")
  public ResponseEntity<UsuarioResponseDto> mostrarDados(@PathVariable Long id) {
    UsuarioResponseDto usuario = service.mostrarDados(id);

    if (usuario == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(usuario);
  }

  @DeleteMapping("/{id}")
  public void deletarUsuario(@PathVariable Long id) {
    service.deletar(id);
  }

  @PutMapping("/curriculo/{id}")
  @SecurityRequirement(name = "Bearer")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioData usuarioAtualizado, Principal principal) {
    Long idDoToken = Long.parseLong(principal.getName());
    if (!id.equals(idDoToken)) {
      throw new org.springframework.security.access.AccessDeniedException("Você não tem permissão para atualizar o perfil de outro usuário.");
    }
    service.atualizarUsuario(usuarioAtualizado);
  }

  @PostMapping("/{userId}/curriculo/upload")
  @SecurityRequirement(name = "Bearer")
  public ResponseEntity<String> uploadCurriculoUsuario(
          @PathVariable Long userId,
          @RequestParam("file") MultipartFile file
          // Removido 'Principal principal'
  ) {
    try {
      String filenameNoServidor = curriculoController.salvarArquivoCurriculo(file);

      String curriculoUrl = "http://localhost:8080/curriculos/download/" + filenameNoServidor;

      service.atualizarCurriculoUrl(userId, curriculoUrl);

      return ResponseEntity.ok(curriculoUrl);

    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo: " + e.getMessage());
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

  @DeleteMapping("/{userId}/curriculo/delete")
  @SecurityRequirement(name = "Bearer")
  public ResponseEntity<String> deletarCurriculoUsuario(
          @PathVariable Long userId
  ) {
    try {
      UsuarioResponseDto usuario = service.mostrarDados(userId);
      String urlAntiga = usuario.getCurriculoUrl();
      String nomeArquivoAntigo = null;

      if (urlAntiga != null && !urlAntiga.isEmpty()) {
        int lastSlashIndex = urlAntiga.lastIndexOf('/');
        if (lastSlashIndex != -1 && lastSlashIndex < urlAntiga.length() - 1) {
          nomeArquivoAntigo = urlAntiga.substring(lastSlashIndex + 1);
        }
      }

      service.atualizarCurriculoUrl(userId, null);

      if (nomeArquivoAntigo != null) {
        curriculoController.deletarArquivoFisico(nomeArquivoAntigo);
      }

      return ResponseEntity.ok("Currículo deletado com sucesso.");
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar currículo: " + e.getMessage());
    }
  }
}
