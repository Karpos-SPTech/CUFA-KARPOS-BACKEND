package cufa.conecta.com.br.resources.empresa;

import cufa.conecta.com.br.application.exception.EmpresaNotFoundException;
import cufa.conecta.com.br.domain.enums.Cargo;
import cufa.conecta.com.br.model.FuncionarioData;
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

  public void atualizar(FuncionarioData funcionario) {
    FuncionarioEntity funcionarioExistente =
        funcionarioDao
            .findById(funcionario.getId())
            .orElseThrow(() -> new EmpresaNotFoundException("Empresa não encontrada"));

    funcionarioExistente.setNome(funcionario.getNome());
    funcionarioExistente.setEmail(funcionario.getEmail());
    funcionarioExistente.setSenha(funcionario.getSenha());
    funcionarioExistente.setCargo(Cargo.fromString(funcionario.getCargo()));

    funcionarioDao.save(funcionarioExistente);
  }

  public void deletar(Long id) {
    FuncionarioEntity funcionario =
        funcionarioDao
            .findById(id)
            .orElseThrow(() -> new EmpresaNotFoundException("Empresa não encontrada"));

    funcionarioDao.delete(funcionario);
  }
}
