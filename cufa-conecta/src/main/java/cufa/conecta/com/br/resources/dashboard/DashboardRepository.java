package cufa.conecta.com.br.resources.dashboard;

import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.dao.PublicacaoDao;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import java.time.LocalDate;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardRepository {
  private final EmpresaDao empresaDao;
  private final UsuarioDao usuarioDao;
  private final PublicacaoDao publicacaoDao;

  public DashboardRepository(
      EmpresaDao empresaDao, UsuarioDao usuarioDao, PublicacaoDao publicacaoDao) {
    this.empresaDao = empresaDao;
    this.usuarioDao = usuarioDao;
    this.publicacaoDao = publicacaoDao;
  }

  public long contarPorDataCadastroEntre(LocalDate inicio, LocalDate fim) {
    return empresaDao.contarCadastrosUltimaSemana(inicio, fim);
  }

  public long contarTodasEmpresas() {
    return empresaDao.count();
  }

  public long contarTodosUsuarios() {
    return usuarioDao.count();
  }

  public long contarPublicacoes() {
    return publicacaoDao.count();
  }
}
