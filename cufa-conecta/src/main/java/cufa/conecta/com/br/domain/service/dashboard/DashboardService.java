package cufa.conecta.com.br.domain.service.dashboard;

import cufa.conecta.com.br.resources.dashboard.DashboardRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
  private final DashboardRepository repository;

  public DashboardService(DashboardRepository repository) {
    this.repository = repository;
  }

  public long contarCadastrosUltimaSemana() {
    LocalDate hoje = LocalDate.now();
    LocalDate seteDiasAtras = hoje.minusDays(7);
    return repository.contarPorDataCadastroEntre(seteDiasAtras, hoje);
  }

  public long contarCadastrosUsuarios() {
    return repository.contarTodosUsuarios();
  }

  public long contarCadastrosEmpresas() {
    return repository.contarTodasEmpresas();
  }

  public long contarPublicacoes() {
    return repository.contarPublicacoes();
  }
}
