package cufa.conecta.com.br.application.controller.usuarios;

import cufa.conecta.com.br.application.dto.request.usuario.CandidaturaRequestDto;
import cufa.conecta.com.br.application.dto.response.empresa.PublicacaoResponseDto;
import cufa.conecta.com.br.application.dto.response.usuario.CandidaturaResponseDto;
import cufa.conecta.com.br.domain.service.usuario.CandidaturaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidatura")
public class CandidaturaController {
  private final CandidaturaService service;

  public CandidaturaController(CandidaturaService service) {
    this.service = service;
  }

      @PostMapping
      @SecurityRequirement(name = "Bearer")
      @ResponseStatus(HttpStatus.CREATED)
      public void criarCandidatura(@RequestBody CandidaturaRequestDto candidaturaDto) {
          service.criarCandidatura(candidaturaDto);
      }

    @GetMapping("/{vagaId}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<CandidaturaResponseDto> listarCandidatosPorVaga(@PathVariable Long vagaId) {
        return ResponseEntity.ok(service.listarCandidatosPorVaga(vagaId));
    }

    @GetMapping("/verificar/{userId}/{vagaId}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Boolean> verificarCandidaturaExistente(@PathVariable Long userId, @PathVariable Long vagaId) {
        boolean existe = service.verificarCandidaturaExistente(userId, vagaId);

        return ResponseEntity.ok(existe);
    }

    @GetMapping("/usuario/{userId}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<PublicacaoResponseDto>> verCandidaturasPorUsuario(@PathVariable Long userId) {
        List<PublicacaoResponseDto> vagasCandidatadas = service.listarPublicacoesCandidatadasPorUsuario(userId);

        return ResponseEntity.ok(vagasCandidatadas);
    }
}
