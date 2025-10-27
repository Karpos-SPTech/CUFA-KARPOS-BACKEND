package cufa.conecta.com.br.resources.empresa.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "publicacao")
public class PublicacaoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idPublicacao;

  @ManyToOne
  @JoinColumn(name = "fkEmpresa")
  private EmpresaEntity empresa;

  @Column(length = 50)
  private String titulo;

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

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
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

  // --- IMPLEMENTAÇÃO MANUAL DE equals() e hashCode() ---
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PublicacaoEntity that = (PublicacaoEntity) o;
   return Objects.equals(idPublicacao, that.idPublicacao);
  }

  @Override
  public int hashCode() {
    // O hashCode também é baseado no idPublicacao.
    return Objects.hash(idPublicacao);
  }

}
