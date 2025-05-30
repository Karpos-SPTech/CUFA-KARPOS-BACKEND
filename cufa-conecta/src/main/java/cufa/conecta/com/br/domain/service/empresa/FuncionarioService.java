package cufa.conecta.com.br.domain.service.empresa;

import cufa.conecta.com.br.application.dto.request.empresa.FuncionarioRequestDto;
import cufa.conecta.com.br.application.dto.response.empresa.FuncionarioResponseDto;
import cufa.conecta.com.br.domain.enums.Cargo;
import cufa.conecta.com.br.resources.empresa.FuncionarioRepository;
import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import cufa.conecta.com.br.resources.empresa.entity.FuncionarioEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

  private final FuncionarioRepository funcionarioRepository;
  private final EmpresaDao empresaDao;
  private final PasswordEncoder passwordEncoder;

  public FuncionarioService(
      FuncionarioRepository funcionarioRepository,
      EmpresaDao empresaDao,
      PasswordEncoder passwordEncoder) {
    this.funcionarioRepository = funcionarioRepository;
    this.empresaDao = empresaDao;
    this.passwordEncoder = passwordEncoder;
  }

  public FuncionarioResponseDto criarFuncionario(FuncionarioRequestDto dto) {
    // Recupera e-mail da empresa logada do contexto de segurança
    String emailEmpresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();

    EmpresaEntity empresa =
        empresaDao
            .findByEmail(emailEmpresaLogada)
            .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

    if (funcionarioRepository.existsByEmail(dto.email)) {
      throw new RuntimeException("Email já está em uso.");
    }

    FuncionarioEntity funcionario = new FuncionarioEntity();
    funcionario.setEmpresa(empresa);
    funcionario.setNome(dto.nome);
    funcionario.setEmail(dto.email);
    funcionario.setSenha(passwordEncoder.encode(dto.senha));
    funcionario.setCargo(Cargo.fromString(dto.cargo));

    FuncionarioEntity salvo = funcionarioRepository.save(funcionario);
    return new FuncionarioResponseDto(salvo);
  }

  public List<FuncionarioResponseDto> listarPorEmpresa(Long fkEmpresa) {
    // Verifica se a empresa autenticada tem permissão para acessar esses dados
    String emailEmpresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();
    EmpresaEntity empresaAutenticada = empresaDao.findByEmail(emailEmpresaLogada)
            .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

    if (!empresaAutenticada.getId().equals(fkEmpresa)) {
      throw new RuntimeException("Acesso negado à empresa solicitada.");
    }

    List<FuncionarioEntity> funcionarios = funcionarioRepository.findByEmpresaId(fkEmpresa);
    return funcionarios.stream()
            .map(FuncionarioResponseDto::new)
            .collect(Collectors.toList());
  }

}
