package cufa.conecta.com.br.resources.empresa.entity;

import cufa.conecta.com.br.domain.enums.Cargo;
import jakarta.persistence.*;

@Entity(name = "funcionarios")
public class FuncionarioEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idFuncionario;

  @ManyToOne
  @JoinColumn(name = "fkEmpresa", nullable = false)
  private EmpresaEntity empresa;

  private String nome;

  @Column(unique = true)
  private String email;

  private String senha;

  @Enumerated(EnumType.STRING)
  private Cargo cargo;

  public Long getIdFuncionario() {
    return idFuncionario;
  }

  public void setIdFuncionario(Long idFuncionario) {
    this.idFuncionario = idFuncionario;
  }

  public EmpresaEntity getEmpresa() {
    return empresa;
  }

  public void setEmpresa(EmpresaEntity empresa) {
    this.empresa = empresa;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }
}
