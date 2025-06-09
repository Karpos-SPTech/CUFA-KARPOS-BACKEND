package cufa.conecta.com.br.model;

import java.time.LocalDate;

public class CandidaturaData {
  Long id;
  Long fkUsuario;
  Long fkPublicacao;
  Long fkEmpresa;
  LocalDate dtCandidatura;

  public CandidaturaData(
      Long fkUsuario, Long fkPublicacao, Long fkEmpresa, LocalDate dtCandidatura) {
    this.fkUsuario = fkUsuario;
    this.fkPublicacao = fkPublicacao;
    this.fkEmpresa = fkEmpresa;
    this.dtCandidatura = dtCandidatura;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getFkUsuario() {
    return fkUsuario;
  }

  public void setFkUsuario(Long fkUsuario) {
    this.fkUsuario = fkUsuario;
  }

  public Long getFkPublicacao() {
    return fkPublicacao;
  }

  public void setFkPublicacao(Long fkPublicacao) {
    this.fkPublicacao = fkPublicacao;
  }

  public Long getFkEmpresa() {
    return fkEmpresa;
  }

  public void setFkEmpresa(Long fkEmpresa) {
    this.fkEmpresa = fkEmpresa;
  }

  public LocalDate getDtCandidatura() {
    return dtCandidatura;
  }

  public void setDtCandidatura(LocalDate dtCandidatura) {
    this.dtCandidatura = dtCandidatura;
  }
}
