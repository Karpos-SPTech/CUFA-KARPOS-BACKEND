package cufa.conecta.com.br.resources.empresa;

import cufa.conecta.com.br.application.dto.response.empresa.FuncionarioTokenDto;
import cufa.conecta.com.br.application.exception.EmpresaNotFoundException;
import cufa.conecta.com.br.config.GerenciadorTokenJwt;
import cufa.conecta.com.br.domain.enums.Cargo;
import cufa.conecta.com.br.model.FuncionarioData;
import cufa.conecta.com.br.resources.empresa.dao.FuncionarioDao;
import cufa.conecta.com.br.resources.empresa.entity.FuncionarioEntity;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class FuncionarioRepository {

  private final FuncionarioDao funcionarioDao;
  private final GerenciadorTokenJwt gerenciadorTokenJwt;

  public FuncionarioRepository(
      FuncionarioDao funcionarioDao, GerenciadorTokenJwt gerenciadorTokenJwt) {
    this.funcionarioDao = funcionarioDao;
    this.gerenciadorTokenJwt = gerenciadorTokenJwt;
  }

  public FuncionarioEntity save(FuncionarioEntity funcionario) {
    return funcionarioDao.save(funcionario);
  }

  public FuncionarioTokenDto autenticar(FuncionarioData funcionario) {
    FuncionarioEntity funcionarioAutenticado = buscarEmpresaPorEmail(funcionario.getEmail());

    Authentication autenticacao =
        new UsernamePasswordAuthenticationToken(
            funcionarioAutenticado, // objeto completo
            null, // não guardar senha
            List.of() // sem roles (ok no seu caso)
            );

    SecurityContextHolder.getContext().setAuthentication(autenticacao);

    String token = gerenciadorTokenJwt.generateToken(autenticacao);
    return new FuncionarioTokenDto(
        funcionarioAutenticado.getNome(), funcionarioAutenticado.getEmail(), token);
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

  private FuncionarioEntity buscarEmpresaPorEmail(String email) {
    return funcionarioDao
        .findByEmail(email)
        .orElseThrow(() -> new EmpresaNotFoundException("Email do usuário não encontrado"));
  }
}
