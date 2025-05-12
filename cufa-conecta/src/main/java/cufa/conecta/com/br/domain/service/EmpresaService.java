package cufa.conecta.com.br.domain.service;

import cufa.conecta.com.br.application.dto.request.empresa.EmpresaRequestDto;
import cufa.conecta.com.br.application.dto.request.LoginDto;
import cufa.conecta.com.br.application.dto.response.empresa.EmpresaResponseDto;
import cufa.conecta.com.br.application.dto.response.empresa.EmpresaTokenDto;
import cufa.conecta.com.br.model.EmpresaData;
import cufa.conecta.com.br.resources.empresa.EmpresaRepository;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
    private final EmpresaRepository repository;
    private final PasswordEncoder passwordEncoder;

    public EmpresaService(EmpresaRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrarEmpresa(EmpresaRequestDto empresaDto) {
        String senhaCriptografada = passwordEncoder.encode(empresaDto.getSenha());

        EmpresaData empresaData = new EmpresaData(
                empresaDto.getNome(),
                empresaDto.getEmail(),
                senhaCriptografada,
                empresaDto.getCep(),
                empresaDto.getNumero(),
                empresaDto.getEndereco(),
                empresaDto.getCnpj(),
                empresaDto.getArea()
        );
        repository.cadastrarEmpresa(empresaData);
    }

    public EmpresaTokenDto login(LoginDto empresaLoginDto) {
        EmpresaData empresaData = new EmpresaData();

        empresaData.setEmail(empresaLoginDto.getEmail());
        empresaData.setSenha(empresaLoginDto.getSenha());

        return repository.autenticar(empresaData);
    }

    public List<EmpresaResponseDto> listarTodos() {
        List<EmpresaEntity> empresasEncontradas = repository.listarTodos();
        return empresasEncontradas.stream()
                .map(empresa -> new EmpresaResponseDto(
                        empresa.getId(),
                        empresa.getNome(),
                        empresa.getEmail(),
                        empresa.getCep(),
                        empresa.getNumero(),
                        empresa.getEndereco(),
                        empresa.getCnpj(),
                        empresa.getArea())
                )
                .collect(Collectors.toList());
    }
    public void atualizarEmpresa(EmpresaData empresa) { repository.atualizar(empresa); }

    public void deletar(Long id) { repository.deletar(id); }
}