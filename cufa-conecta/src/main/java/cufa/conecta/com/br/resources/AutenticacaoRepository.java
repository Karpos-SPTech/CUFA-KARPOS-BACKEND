package cufa.conecta.com.br.resources;

import cufa.conecta.com.br.application.exception.NotFoundException;
import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.dao.FuncionarioDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import cufa.conecta.com.br.resources.empresa.entity.FuncionarioEntity;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoRepository implements UserDetailsService {
  private final UsuarioDao usuarioDao;
  private final EmpresaDao empresaDao;
  private final FuncionarioDao funcionarioDao;

  public AutenticacaoRepository(
      UsuarioDao usuarioDao, EmpresaDao empresaDao, FuncionarioDao funcionarioDao) {
    this.usuarioDao = usuarioDao;
    this.empresaDao = empresaDao;
    this.funcionarioDao = funcionarioDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UsuarioEntity> usuarioOpt = usuarioDao.findByEmail(username);

    Optional<FuncionarioEntity> funcionarioOpt = funcionarioDao.findByEmail(username);

    if (funcionarioOpt.isPresent()) {
      FuncionarioEntity funcionario = funcionarioOpt.get();
      return new User(funcionario.getEmail(), funcionario.getSenha(), new ArrayList<>());
    }

    if (usuarioOpt.isPresent()) {
      UsuarioEntity usuario = usuarioOpt.get();
      return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }

    Optional<EmpresaEntity> empresaOpt = empresaDao.findByEmail(username);

    if (empresaOpt.isPresent()) {
      EmpresaEntity empresa = empresaOpt.get();
      return new User(empresa.getEmail(), empresa.getSenha(), new ArrayList<>());
    }

    throw new NotFoundException("Usuário ou empresa com este e-mail não foi encontrado.");
  }
}
