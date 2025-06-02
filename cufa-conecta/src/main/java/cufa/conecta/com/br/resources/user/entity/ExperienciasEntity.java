package cufa.conecta.com.br.resources.user.entity;

import jakarta.persistence.*;

@Entity(name = "experiencias")
public class ExperienciasEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column Long fkUsuario;

  @Column String cargo;

  @Column String empresa;

  @Column String dt_inicio;

  @Column String dt_fim;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getFkUsuario() {
    return fkUsuario;
  }

  public void setFk_usuario(Long fkUsuario) {
    this.fkUsuario = fkUsuario;
  }

  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public String getEmpresa() {
    return empresa;
  }

  public void setEmpresa(String empresa) {
    this.empresa = empresa;
  }

  public String getDt_inicio() {
    return dt_inicio;
  }

  public void setDt_inicio(String dt_inicio) {
    this.dt_inicio = dt_inicio;
  }

  public String getDt_fim() {
    return dt_fim;
  }

  public void setDt_fim(String dt_fim) {
    this.dt_fim = dt_fim;
  }
}
