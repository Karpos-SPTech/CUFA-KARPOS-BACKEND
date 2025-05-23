package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.dto.request.empresa.PublicacaoRequestDto;
import cufa.conecta.com.br.application.dto.response.empresa.PublicacaoResponseDto;
import cufa.conecta.com.br.domain.service.empresa.PublicacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
