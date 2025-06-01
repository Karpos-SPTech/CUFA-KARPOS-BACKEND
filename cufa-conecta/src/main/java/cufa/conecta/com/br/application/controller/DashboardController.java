package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.domain.service.dashboard.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboards")
public class DashboardController {
  private final DashboardService service;

  public DashboardController(DashboardService service) {
    this.service = service;
  }

  @GetMapping("/cadastros-semana")
  public ResponseEntity<Long> contarCadastrosUltimaSemana() {
    long total = service.contarCadastrosUltimaSemana();
    return ResponseEntity.ok(total);
  }

  @GetMapping("/cadastros-usuarios")
  public ResponseEntity<Long> contarCadastrosUsuarios() {
    long total = service.contarCadastrosUsuarios();
    return ResponseEntity.ok(total);
  }

  @GetMapping("/cadastros-empresas")
  public ResponseEntity<Long> contarCadastrosEmpresas() {
    long total = service.contarCadastrosEmpresas();
    return ResponseEntity.ok(total);
  }

  @GetMapping("/publicacoes")
  public ResponseEntity<Long> contarPublicacoes() {
    long total = service.contarPublicacoes();
    return ResponseEntity.ok(total);
  }
}
