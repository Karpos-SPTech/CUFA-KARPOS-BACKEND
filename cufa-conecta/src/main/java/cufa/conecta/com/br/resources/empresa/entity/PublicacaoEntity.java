package cufa.conecta.com.br.resources.empresa.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "publicacao")
public class PublicacaoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPublicacao;

  @ManyToOne
  @JoinColumn(name = "fkEmpresa")
  private EmpresaEntity empresa;

  @Column(columnDefinition = "TEXT")
  private String descricao;

  @Column(length = 10)
  private String tipoContrato;

  private LocalDateTime dtExpiracao;

  private LocalDateTime dtPublicacao;

  public Long getIdPublicacao() {
    return idPublicacao;
  }

  public void setIdPublicacao(Long idPublicacao) {
    this.idPublicacao = idPublicacao;
  }

  public EmpresaEntity getEmpresa() {
    return empresa;
  }

  public void setEmpresa(EmpresaEntity empresa) {
    this.empresa = empresa;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getTipoContrato() {
    return tipoContrato;
  }

  public void setTipoContrato(String tipoContrato) {
    this.tipoContrato = tipoContrato;
  }

  public LocalDateTime getDtExpiracao() {
    return dtExpiracao;
  }

  public void setDtExpiracao(LocalDateTime dtExpiracao) {
    this.dtExpiracao = dtExpiracao;
  }

  public LocalDateTime getDtPublicacao() {
    return dtPublicacao;
  }

  public void setDtPublicacao(LocalDateTime dtPublicacao) {
    this.dtPublicacao = dtPublicacao;
  }
}
