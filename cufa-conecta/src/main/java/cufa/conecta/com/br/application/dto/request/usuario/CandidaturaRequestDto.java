package cufa.conecta.com.br.application.dto.request.usuario;

import jakarta.validation.constraints.NotNull;

public class CandidaturaRequestDto {
  @NotNull Long fkUsuario;
  @NotNull Long fkPublicacao;
  @NotNull Long fkEmpresa;

  public CandidaturaRequestDto(Long fkUsuario, Long fkPublicacao, Long fkEmpresa) {
    this.fkUsuario = fkUsuario;
    this.fkPublicacao = fkPublicacao;
    this.fkEmpresa = fkEmpresa;
  }

  //    public CandidaturaData toModel() {
  //        if (fkUsuario == null || fkPublicacao == null || fkEmpresa == null) {
  //            throw new BadRequestException("Campos não podem ser nulos");
  //        }
  //        return new CandidaturaData(fkUsuario, fkPublicacao, fkEmpresa);
  //    }

  public Long getFkUsuario() {
    return fkUsuario;
  }

  public Long getFkPublicacao() {
    return fkPublicacao;
  }

  public Long getFkEmpresa() {
    return fkEmpresa;
  }
}
