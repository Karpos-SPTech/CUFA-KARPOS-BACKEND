package cufa.conecta.com.br.application.dto.response.empresa;

import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;
import java.time.LocalDateTime;

public class PublicacaoResponseDto {
  public Long idPublicacao;
  public String titulo;
  public String descricao;
  public String tipoContrato;
  public LocalDateTime dtExpiracao;
  public LocalDateTime dtPublicacao;
  public String nomeEmpresa;
  public Long fkEmpresa;

  public PublicacaoResponseDto(PublicacaoEntity entity) {
    this.idPublicacao = entity.getIdPublicacao();
    this.titulo = entity.getTitulo();
    this.descricao = entity.getDescricao();
    this.tipoContrato = entity.getTipoContrato();
    this.dtExpiracao = entity.getDtExpiracao();
    this.dtPublicacao = entity.getDtPublicacao();
    this.nomeEmpresa = entity.getEmpresa().getNome();
    this.fkEmpresa = entity.getEmpresa().getId();
  }
}
