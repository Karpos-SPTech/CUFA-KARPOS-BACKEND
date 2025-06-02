package cufa.conecta.com.br.resources.user;

import cufa.conecta.com.br.application.dto.response.usuario.UsuarioResponseDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioTokenDto;
import cufa.conecta.com.br.application.exception.UsuarioBadRequest;
import cufa.conecta.com.br.application.exception.UsuarioNotFoundException;
import cufa.conecta.com.br.config.GerenciadorTokenJwt;
import cufa.conecta.com.br.model.UsuarioData;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {
  private final GerenciadorTokenJwt gerenciadorTokenJwt;
  private final AuthenticationManager authenticationManager;
  private final UsuarioDao usuarioDao;

  public UsuarioRepository(
      GerenciadorTokenJwt gerenciadorTokenJwt,
      AuthenticationManager authenticationManager,
      UsuarioDao usuarioDao) {
    this.gerenciadorTokenJwt = gerenciadorTokenJwt;
    this.authenticationManager = authenticationManager;
    this.usuarioDao = usuarioDao;
  }

  // ------------------------- Métodos do usuário -----------------------------

  public void cadastrarUsuario(UsuarioData userDto) {
    UsuarioEntity usuarioEntity = toEntity(userDto);

    boolean emailExistente = usuarioDao.findByEmail(userDto.getEmail()).isPresent();

    if (emailExistente) {
      throw new UsuarioBadRequest("E-mail já cadastrado!");
    }

    usuarioDao.save(usuarioEntity);
  }

  public UsuarioTokenDto autenticar(UsuarioData usuario) {
    authenticateCredentials(usuario.getEmail(), usuario.getSenha());

    UsuarioEntity usuarioAutenticado = buscarUsuarioPorEmail(usuario.getEmail());
    SecurityContextHolder.getContext()
        .setAuthentication(
            new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));

    String token =
        gerenciadorTokenJwt.generateToken(
            new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));

    return new UsuarioTokenDto(
        usuarioAutenticado.getId(),
        usuarioAutenticado.getNome(),
        usuarioAutenticado.getEmail(),
        token);
  }

  public List<UsuarioEntity> listarTodos() {
    return usuarioDao.findAll();
  }

  public UsuarioResponseDto mostrarDados(Long id) {
    UsuarioEntity usuario =
        usuarioDao
            .findById(id)
            .orElseThrow(
                () -> new UsuarioNotFoundException("Usuário com id " + id + " não encontrado"));

    return new UsuarioResponseDto(
        usuario.getNome(),
        usuario.getCpf(),
        usuario.getTelefone(),
        usuario.getEscolaridade(),
        usuario.getDt_nascimento(),
        usuario.getEstado_civil(),
        usuario.getEstado(),
        usuario.getCidade(),
        usuario.getBiografia());
  }

  public void atualizar(UsuarioData usuario) {
    UsuarioEntity usuarioExistente =
        usuarioDao
            .findById(usuario.getId())
            .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

    usuarioExistente.setNome(usuario.getNome());
    usuarioExistente.setCpf(usuario.getCpf());
    usuarioExistente.setTelefone(usuario.getTelefone());
    usuarioExistente.setEscolaridade(usuario.getEscolaridade());
    usuarioExistente.setDt_nascimento(usuario.getDtNascimento());
    usuarioExistente.setEstado_civil(usuario.getEstado_civil());
    usuarioExistente.setEstado(usuario.getEstado());
    usuarioExistente.setCidade(usuario.getCidade());
    usuarioExistente.setBiografia(usuario.getBiografia());

    usuarioDao.save(usuarioExistente);
  }

  public void deletar(Long id) {
    UsuarioEntity usuario =
        usuarioDao
            .findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));

    usuarioDao.delete(usuario);
  }

  //     ------------------ Métodos privados --------------

  private UsuarioEntity toEntity(UsuarioData usuarioData) {
    UsuarioEntity entity = new UsuarioEntity();
    entity.setNome(usuarioData.getNome());
    entity.setEmail(usuarioData.getEmail());
    entity.setSenha(usuarioData.getSenha());
    entity.setCpf(usuarioData.getCpf());
    entity.setTelefone(usuarioData.getTelefone());
    entity.setEscolaridade(usuarioData.getEscolaridade());
    entity.setDt_nascimento(usuarioData.getDtNascimento());
    entity.setEstado_civil(usuarioData.getEstado_civil());
    entity.setEstado(usuarioData.getEstado());
    entity.setCidade(usuarioData.getCidade());
    entity.setBiografia(usuarioData.getBiografia());

    return entity;
  }

  private void authenticateCredentials(String email, String senha) {
    Authentication credentials = new UsernamePasswordAuthenticationToken(email, senha);

    authenticationManager.authenticate(credentials);

    SecurityContextHolder.getContext().setAuthentication(credentials);
  }

  private UsuarioEntity buscarUsuarioPorEmail(String email) {
    return usuarioDao
        .findByEmail(email)
        .orElseThrow(() -> new UsuarioNotFoundException("Email do usuário não encontrado"));
  }
}
