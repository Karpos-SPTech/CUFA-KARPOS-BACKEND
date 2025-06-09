package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.dto.request.usuario.ExperienciaRequestDto;
import cufa.conecta.com.br.application.dto.response.usuario.ExperienciaResponseDto;
import cufa.conecta.com.br.domain.service.usuario.ExperienciaService;
import cufa.conecta.com.br.model.ExperienciaData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/experiencias")
public class ExperienciaController {

  private final ExperienciaService experienciaService;

  public ExperienciaController(ExperienciaService experienciaService) {
    this.experienciaService = experienciaService;
  }

  @PostMapping
  @SecurityRequirement(name = "Bearer")
  @ResponseStatus(HttpStatus.CREATED)
  public void criarExperiencia(@RequestBody @Valid ExperienciaRequestDto experienciaDto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    experienciaService.criarExperiencia(experienciaDto, email);
  }

  @GetMapping("/{id}")
  @SecurityRequirement(name = "Bearer")
  public ResponseEntity<List<ExperienciaResponseDto>> listarExperienciasPorUsuario(
      @PathVariable Long id) {
    List<ExperienciaResponseDto> experienciasEncontradas = experienciaService.listarPorUsuario(id);
    if (experienciasEncontradas.isEmpty()) {
      return ResponseEntity.status(204).build();
    }
    return ResponseEntity.status(200).body(experienciasEncontradas);
  }

  @PutMapping("/{id}")
  @SecurityRequirement(name = "Bearer")
  @ResponseStatus(HttpStatus.OK)
  public void atualizarExperiencia(
      @PathVariable Long id, @RequestBody ExperienciaRequestDto experienciaDto) {
    ExperienciaData experienciaParaAtualizar = experienciaDto.toModel();
    experienciaParaAtualizar.setId(id);
    experienciaService.atualizarExperiencia(experienciaParaAtualizar);
  }

  @DeleteMapping("/{id}")
  @SecurityRequirement(name = "Bearer")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarExperiencia(@PathVariable Long id) {
    experienciaService.deletar(id);
  }
}
