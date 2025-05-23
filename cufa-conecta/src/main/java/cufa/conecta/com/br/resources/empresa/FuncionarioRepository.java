package cufa.conecta.com.br.resources.empresa;

import cufa.conecta.com.br.resources.empresa.dao.FuncionarioDao;
import cufa.conecta.com.br.resources.empresa.entity.FuncionarioEntity;
import java.util.Optional;
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

  public Optional<FuncionarioEntity> findByEmail(String email) {
    return funcionarioDao.findByEmail(email);
  }

  public boolean existsByEmail(String email) {
    return funcionarioDao.findByEmail(email).isPresent();
  }
}
