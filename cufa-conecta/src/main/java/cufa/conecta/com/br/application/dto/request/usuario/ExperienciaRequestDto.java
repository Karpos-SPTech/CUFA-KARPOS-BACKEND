package cufa.conecta.com.br.application.dto.request.usuario;

import cufa.conecta.com.br.application.exception.BadRequestException;
import cufa.conecta.com.br.model.ExperienciaData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ExperienciaRequestDto {
  @NotBlank String cargo;
  @NotBlank String empresa;
  @NotNull String dtInicio;
  @NotNull String dtFim;

  public ExperienciaRequestDto(String cargo, String empresa, String dtInicio, String dtFim) {
    this.cargo = cargo;
    this.empresa = empresa;
    this.dtInicio = dtInicio;
    this.dtFim = dtFim;
  }

  public ExperienciaData toModel() {
    if (cargo == null || empresa == null || dtInicio == null || dtFim == null) {
      throw new BadRequestException("Campos não podem ser nulos");
    }
    return new ExperienciaData(cargo, empresa, dtInicio, dtFim);
  }

  public String getCargo() {
    return cargo;
  }

  public String getEmpresa() {
    return empresa;
  }

  public String getDtInicio() {
    return dtInicio;
  }

  public String getDtFim() {
    return dtFim;
  }
}
