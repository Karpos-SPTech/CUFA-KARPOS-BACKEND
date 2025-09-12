package cufa.conecta.com.br.model;

public class ExperienciaData {
  Long id;
  Long fkUsuario;
  String cargo;
  String empresa;
  String dtInicio;
  String dtFim;

  public ExperienciaData(
      String cargo, String empresa, String dtInicio, String dtFim, Long fkUsuario) {
    this.cargo = cargo;
    this.empresa = empresa;
    this.dtInicio = dtInicio;
    this.dtFim = dtFim;
    this.fkUsuario = fkUsuario;
  }

  public ExperienciaData(String cargo, String empresa, String dtInicio, String dtFim) {
    this.cargo = cargo;
    this.empresa = empresa;
    this.dtInicio = dtInicio;
    this.dtFim = dtFim;
  }

  public Long getId() {
    return id;
  }

  public Long getFkUsuario() {
    return fkUsuario;
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

  public void setId(Long id) {
    this.id = id;
  }

  public void setFkUsuario(Long fkUsuario) {
    this.fkUsuario = fkUsuario;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public void setEmpresa(String empresa) {
    this.empresa = empresa;
  }

  public void setDtInicio(String dtInicio) {
    this.dtInicio = dtInicio;
  }

  public void setDtFim(String dtFim) {
    this.dtFim = dtFim;
  }
}
