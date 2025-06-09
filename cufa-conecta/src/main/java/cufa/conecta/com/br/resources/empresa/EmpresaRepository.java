package cufa.conecta.com.br.resources.empresa;

import cufa.conecta.com.br.application.dto.response.empresa.EmpresaTokenDto;
import cufa.conecta.com.br.application.exception.EmpresaBadRequest;
import cufa.conecta.com.br.application.exception.EmpresaNotFoundException;
import cufa.conecta.com.br.config.GerenciadorTokenJwt;
import cufa.conecta.com.br.model.EmpresaData;
import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import java.util.List;
import java.util.Optional;

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

  public void cadastrarEmpresa(EmpresaData empresaDto) {
    EmpresaEntity empresaEntity = toEntity(empresaDto);

    boolean emailExistente = empresaDao.findByEmail(empresaDto.getEmail()).isPresent();

    if (emailExistente) { throw new EmpresaBadRequest("E-mail já cadastrado!"); }

    empresaDao.save(empresaEntity);
  }

  public EmpresaTokenDto autenticar(EmpresaData dadosLogin) {
    String email = dadosLogin.getEmail();
    String senha = dadosLogin.getSenha();

    validarEmpresa(email, senha);

    EmpresaEntity empresaAutenticada = buscarEmpresaPorEmail(email);

    Long idEmpresa = empresaAutenticada.getId();
    String emailEmpresa = empresaAutenticada.getEmail();
    String nomeEmpresa = empresaAutenticada.getNome();

    UsernamePasswordAuthenticationToken dadosAutenticados =
            new UsernamePasswordAuthenticationToken(email, senha);

    SecurityContextHolder.getContext().setAuthentication(dadosAutenticados);

    String tokenJwt = gerenciadorTokenJwt.generateToken(dadosAutenticados);

    return new EmpresaTokenDto(idEmpresa, emailEmpresa, nomeEmpresa, tokenJwt);
  }

  public List<EmpresaEntity> listarTodos() {
    return empresaDao.findAll();
  }

  public EmpresaEntity buscarPorId(Long id) {
    return empresaDao.findById(id)
            .orElseThrow(() -> new EmpresaNotFoundException("Empresa com ID " + id + " não encontrada"));
  }

  public Optional<EmpresaEntity> findByEmail(String email){return empresaDao.findByEmail(email);}

  public void atualizarDados(EmpresaData empresaAtualizada) {
    EmpresaEntity empresaAtual =
            empresaDao.findById(empresaAtualizada.getId())
                    .orElseThrow(() -> new EmpresaNotFoundException("Empresa não encontrada"));

    atualizarDados(empresaAtualizada, empresaAtual);

    empresaDao.save(empresaAtual);
  }

  public void atualizarDadosParcial(EmpresaData empresaParcial) {
    EmpresaEntity empresaAtual =
            empresaDao.findById(empresaParcial.getId())
                    .orElseThrow(() -> new EmpresaNotFoundException("Empresa não encontrada"));

    atualizarDadosParcial(empresaParcial, empresaAtual);

    empresaDao.save(empresaAtual);
  }


  public void atualizarBiografia(EmpresaEntity empresa) {
    empresaDao.save(empresa);
  }

  //     ------------------ Métodos privados --------------

  private EmpresaEntity toEntity(EmpresaData empresaData) {
    EmpresaEntity entity = new EmpresaEntity();
    atualizarDados(empresaData, entity);

    return entity;
  }

  private void validarEmpresa(String email, String senha) {
    Authentication autenticacao = new UsernamePasswordAuthenticationToken(email, senha);

    authenticationManager.authenticate(autenticacao);

    SecurityContextHolder.getContext().setAuthentication(autenticacao);
  }

  private EmpresaEntity buscarEmpresaPorEmail(String email) {
    return empresaDao.findByEmail(email)
        .orElseThrow(() -> new EmpresaNotFoundException("Email do usuário não encontrado"));
  }

  private void atualizarDados(EmpresaData empresaAtualizada, EmpresaEntity empresaAtual) {
    empresaAtual.setNome(empresaAtualizada.getNome());
    empresaAtual.setEmail(empresaAtualizada.getEmail());
    empresaAtual.setSenha(empresaAtualizada.getSenha());
    empresaAtual.setCep(empresaAtualizada.getCep());
    empresaAtual.setNumero(empresaAtualizada.getNumero());
    empresaAtual.setEndereco(empresaAtualizada.getEndereco());
    empresaAtual.setCnpj(empresaAtualizada.getCnpj());
    empresaAtual.setArea(empresaAtualizada.getArea());
  }

  private void atualizarDadosParcial(EmpresaData parcial, EmpresaEntity atual) {
    if (parcial.getNome() != null) {
      atual.setNome(parcial.getNome());
    }
    if (parcial.getCep() != null) {
      atual.setCep(parcial.getCep());
    }
    if (parcial.getEndereco() != null) {
      atual.setEndereco(parcial.getEndereco());
    }
    if (parcial.getNumero() != null) {
      atual.setNumero(parcial.getNumero());
    }
  }
}
