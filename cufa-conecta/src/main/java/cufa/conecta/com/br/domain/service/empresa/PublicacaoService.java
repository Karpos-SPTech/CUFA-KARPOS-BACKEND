package cufa.conecta.com.br.domain.service.empresa;

import cufa.conecta.com.br.application.dto.request.empresa.PublicacaoRequestDto;
import cufa.conecta.com.br.application.dto.response.empresa.PublicacaoResponseDto;
import cufa.conecta.com.br.resources.empresa.PublicacaoRepository;
import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;
import java.time.LocalDateTime;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PublicacaoService {

  private final PublicacaoRepository repository;
  private final EmpresaDao empresaDao;

  public PublicacaoService(PublicacaoRepository repository, EmpresaDao empresaDao) {
    this.repository = repository;
    this.empresaDao = empresaDao;
  }

  public PublicacaoResponseDto criarPublicacao(PublicacaoRequestDto dto) {
    String emailEmpresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();

    EmpresaEntity empresa =
        empresaDao
            .findByEmail(emailEmpresaLogada)
            .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

    PublicacaoEntity publicacao = new PublicacaoEntity();
    publicacao.setEmpresa(empresa);
    publicacao.setDescricao(dto.descricao);
    publicacao.setTipoContrato(dto.tipoContrato);
    publicacao.setDtExpiracao(dto.dtExpiracao);
    publicacao.setDtPublicacao(LocalDateTime.now());

    PublicacaoEntity salvo = repository.save(publicacao);

    return new PublicacaoResponseDto(salvo);
  }
}
