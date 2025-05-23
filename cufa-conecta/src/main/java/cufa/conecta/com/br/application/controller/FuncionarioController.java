package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.dto.request.empresa.FuncionarioRequestDto;
import cufa.conecta.com.br.application.dto.response.empresa.FuncionarioResponseDto;
import cufa.conecta.com.br.domain.service.empresa.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

  private final FuncionarioService service;

  public FuncionarioController(FuncionarioService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<FuncionarioResponseDto> criar(@RequestBody FuncionarioRequestDto dto) {
    FuncionarioResponseDto response = service.criarFuncionario(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
