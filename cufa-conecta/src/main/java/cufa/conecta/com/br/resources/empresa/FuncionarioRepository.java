package cufa.conecta.com.br.resources.empresa;

import cufa.conecta.com.br.resources.empresa.dao.FuncionarioDao;
import cufa.conecta.com.br.resources.empresa.entity.FuncionarioEntity;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class FuncionarioRepository {

  private final FuncionarioDao funcionarioDao;

  public FuncionarioRepository(FuncionarioDao funcionarioDao) {
    this.funcionarioDao = funcionarioDao;
  }

  public FuncionarioEntity save(FuncionarioEntity funcionario) {
    return funcionarioDao.save(funcionario);
  }

  public boolean existsByEmail(String email) {
    return funcionarioDao.findByEmail(email).isPresent();
  }

  public List<FuncionarioEntity> findByEmpresaId(Long empresaId) {
    return funcionarioDao.findByEmpresaId(empresaId);
  }

}
