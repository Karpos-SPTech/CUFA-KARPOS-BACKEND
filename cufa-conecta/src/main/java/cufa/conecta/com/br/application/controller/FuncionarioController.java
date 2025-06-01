package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.dto.request.empresa.FuncionarioRequestDto;
import cufa.conecta.com.br.application.dto.response.empresa.FuncionarioResponseDto;
import cufa.conecta.com.br.domain.service.empresa.FuncionarioService;
import cufa.conecta.com.br.model.FuncionarioData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
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

  @GetMapping("/{fkEmpresa}")
  public ResponseEntity<List<FuncionarioResponseDto>> listarPorEmpresa(
      @PathVariable Long fkEmpresa) {
    List<FuncionarioResponseDto> funcionarios = service.listarPorEmpresa(fkEmpresa);

    return ResponseEntity.ok(funcionarios);
  }

  @PostMapping("/{id}")
  public void atualizar(@PathVariable Long id, @RequestBody FuncionarioRequestDto funcionario) {
    FuncionarioData funcionarioParaAtualizar = funcionario.toModel();

    funcionarioParaAtualizar.setId(id);
    service.atualizar(funcionarioParaAtualizar);
  }

  @DeleteMapping("/{id}")
  @SecurityRequirement(name = "Bearer")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarFuncionario(@PathVariable Long id) {
    service.deletar(id);
  }
}
