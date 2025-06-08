package cufa.conecta.com.br.application.dto.request.usuario;

import jakarta.validation.constraints.NotNull;

public class CandidaturaRequestDto {
  @NotNull Long fkUsuario;
  @NotNull Long fkPublicacao;
  @NotNull Long fkEmpresa;

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
}
