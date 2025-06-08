package cufa.conecta.com.br.application.dto.response.usuario;

public class ExperienciaResponseDto {
  private Long id;
  private String cargo;
  private String empresa;
  private String dtInicio;
  private String dtFim;

  public ExperienciaResponseDto(
      Long id, String cargo, String empresa, String dtInicio, String dtFim) {
    this.id = id;
    this.cargo = cargo;
    this.empresa = empresa;
    this.dtInicio = dtInicio;
    this.dtFim = dtFim;
  }

  public ExperienciaResponseDto(String cargo, String empresa, String dtInicio, String dtFim) {
    this.cargo = cargo;
    this.empresa = empresa;
    this.dtInicio = dtInicio;
    this.dtFim = dtFim;
  }

  // Getters
  public Long getId() {
    return id;
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
