package cufa.conecta.com.br.resources.empresa;

import cufa.conecta.com.br.application.dto.response.empresa.EmpresaTokenDto;
import cufa.conecta.com.br.application.exception.EmpresaBadRequest;
import cufa.conecta.com.br.application.exception.EmpresaNotFoundException;
import cufa.conecta.com.br.config.GerenciadorTokenJwt;
import cufa.conecta.com.br.model.EmpresaData;
import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public class EmpresaRepository {
  private final GerenciadorTokenJwt gerenciadorTokenJwt;
  private final AuthenticationManager authenticationManager;
  private final EmpresaDao empresaDao;

  public EmpresaRepository(
      GerenciadorTokenJwt gerenciadorTokenJwt,
      AuthenticationManager authenticationManager,
      EmpresaDao empresaDao) {
    this.gerenciadorTokenJwt = gerenciadorTokenJwt;
    this.authenticationManager = authenticationManager;
    this.empresaDao = empresaDao;
  }

  // ------------------------- Métodos do usuário -----------------------------

  public void cadastrarEmpresa(EmpresaData empresaDto) {
    EmpresaEntity empresaEntity = toEntity(empresaDto);

    boolean emailExistente = empresaDao.findByEmail(empresaDto.getEmail()).isPresent();

    if (emailExistente) {
      throw new EmpresaBadRequest("E-mail já cadastrado!");
    }

    empresaDao.save(empresaEntity);
  }

  public EmpresaTokenDto autenticar(EmpresaData empresa) {
    EmpresaEntity empresaAutenticada = buscarEmpresaPorEmail(empresa.getEmail());

    Authentication autenticacao =
        new UsernamePasswordAuthenticationToken(
            empresaAutenticada, // objeto completo
            null, // não guardar senha
            List.of() // sem roles (ok no seu caso)
            );

    SecurityContextHolder.getContext().setAuthentication(autenticacao);

    String token = gerenciadorTokenJwt.generateToken(autenticacao);
    return new EmpresaTokenDto(empresaAutenticada.getNome(), empresaAutenticada.getEmail(), token);
  }

  public List<EmpresaEntity> listarTodos() {
    return empresaDao.findAll();
  }

  public void atualizar(EmpresaData empresa) {
    EmpresaEntity empresaExistente =
        empresaDao
            .findById(empresa.getId())
            .orElseThrow(() -> new EmpresaNotFoundException("Empresa não encontrada"));

    empresaExistente.setNome(empresa.getNome());
    empresaExistente.setEmail(empresa.getEmail());
    empresaExistente.setSenha(empresa.getSenha());
    empresaExistente.setCep(empresa.getCep());
    empresaExistente.setNumero(empresa.getNumero());
    empresaExistente.setEndereco(empresa.getEndereco());
    empresaExistente.setCnpj(empresa.getCnpj());
    empresaExistente.setArea(empresa.getArea());

    empresaDao.save(empresaExistente);
  }

  public void deletar(Long id) {
    EmpresaEntity empresa =
        empresaDao
            .findById(id)
            .orElseThrow(() -> new EmpresaNotFoundException("Empresa não encontrada"));

    empresaDao.delete(empresa);
  }

  public void atualizarBiografia(EmpresaEntity empresa) {
    empresaDao.save(empresa);
  }

  //     ------------------ Métodos privados --------------

  private EmpresaEntity toEntity(EmpresaData empresaData) {
    EmpresaEntity entity = new EmpresaEntity();
    entity.setNome(empresaData.getNome());
    entity.setEmail(empresaData.getEmail());
    entity.setSenha(empresaData.getSenha());
    entity.setCep(empresaData.getCep());
    entity.setNumero(empresaData.getNumero());
    entity.setEndereco(empresaData.getEndereco());
    entity.setCnpj(empresaData.getCnpj());
    entity.setArea(empresaData.getArea());

    return entity;
  }

  private void authenticateCredentials(String email, String senha) {
    Authentication credentials = new UsernamePasswordAuthenticationToken(email, senha);

    authenticationManager.authenticate(credentials);

    SecurityContextHolder.getContext().setAuthentication(credentials);
  }

  private EmpresaEntity buscarEmpresaPorEmail(String email) {
    return empresaDao
        .findByEmail(email)
        .orElseThrow(() -> new EmpresaNotFoundException("Email do usuário não encontrado"));
  }
}
