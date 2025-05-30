package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.dto.request.empresa.PublicacaoRequestDto;
import cufa.conecta.com.br.application.dto.response.empresa.PublicacaoResponseDto;
import cufa.conecta.com.br.domain.service.empresa.PublicacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

  private final PublicacaoService service;

  public PublicacaoController(PublicacaoService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<PublicacaoResponseDto> criar(@RequestBody PublicacaoRequestDto dto) {
    PublicacaoResponseDto response = service.criarPublicacao(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/all")
  public ResponseEntity<List<PublicacaoResponseDto>> listarTodasPublicacoes() {
    List<PublicacaoResponseDto> lista = service.listarTodasPublicacoes();
    return ResponseEntity.ok(lista);
  }

  @GetMapping
  @SecurityRequirement(name = "Bearer")
  public ResponseEntity<List<PublicacaoResponseDto>> listarPorEmpresa() {
    List<PublicacaoResponseDto> lista = service.listarPublicacoesDaEmpresa();
    return ResponseEntity.ok(lista);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PublicacaoResponseDto> editar(
          @PathVariable Long id, @RequestBody PublicacaoRequestDto dto) {
    PublicacaoResponseDto response = service.editarPublicacao(id, dto);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    service.deletarPublicacao(id);
    return ResponseEntity.noContent().build();
  }
}
