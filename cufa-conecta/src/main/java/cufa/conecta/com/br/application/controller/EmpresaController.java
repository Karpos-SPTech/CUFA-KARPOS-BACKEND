package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.documentation.EmpresaControllerDoc;
import cufa.conecta.com.br.application.dto.request.LoginDto;
import cufa.conecta.com.br.application.dto.request.empresa.EmpresaRequestDto;
import cufa.conecta.com.br.application.dto.response.empresa.EmpresaResponseDto;
import cufa.conecta.com.br.application.dto.response.empresa.EmpresaTokenDto;
import cufa.conecta.com.br.application.exception.EmpresaNotFoundException;
import cufa.conecta.com.br.domain.service.empresa.EmpresaService;
import cufa.conecta.com.br.model.EmpresaData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController implements EmpresaControllerDoc {
  private final EmpresaService service;

  public EmpresaController(EmpresaService service) {
    this.service = service;
  }

  @PostMapping
  @SecurityRequirement(name = "Bearer")
  @ResponseStatus(HttpStatus.CREATED)
  public void cadastrarEmpresa(@RequestBody @Valid EmpresaRequestDto empresaDto) {
    service.cadastrarEmpresa(empresaDto);
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<EmpresaTokenDto> login(@RequestBody LoginDto empresaLoginDto) {
    EmpresaTokenDto empresaToken = service.login(empresaLoginDto);

    ResponseCookie cookie =
        ResponseCookie.from("jwt", empresaToken.getToken())
            .httpOnly(true)
            .path("/")
            .maxAge(3600)
            .sameSite("None").secure(true)
            .build();

    return ResponseEntity.ok()
        .header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new EmpresaTokenDto(empresaToken.getId(), empresaToken.getNome(), empresaToken.getEmail(), null));
  }

  @GetMapping
  @SecurityRequirement(name = "Bearer")
  public ResponseEntity<List<EmpresaResponseDto>> listarEmpresas() {
    List<EmpresaResponseDto> empresasEncontradas = service.listarTodos();

    if (empresasEncontradas.isEmpty()) {
      throw new EmpresaNotFoundException("Não há empresas encontradas");
    }
    return ResponseEntity.status(200).body(empresasEncontradas);
  }

  @PutMapping("/{id}")
  public void atualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaRequestDto empresaDto) {
    EmpresaData empresaAtualizada = empresaDto.toModel();

    empresaAtualizada.setId(id);

    service.atualizarEmpresa(empresaAtualizada);
  }
}
