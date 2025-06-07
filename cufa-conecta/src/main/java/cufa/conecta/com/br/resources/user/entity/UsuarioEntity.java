package cufa.conecta.com.br.resources.user.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity(name = "usuarios")
public class UsuarioEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column String nome;

  @Column String email;

  @Column String senha;

  @Column String cpf;

  @Column String telefone;

  @Column String escolaridade;

  @Column LocalDate dt_nascimento;

  @Column String estado_civil;

  @Column String estado;

  @Column String cidade;

  @Column String biografia;

  @Column String curriculoUrl;

  public String getCurriculoUrl() {
    return curriculoUrl;
  }

  public void setCurriculoUrl(String curriculoUrl) {
    this.curriculoUrl = curriculoUrl;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEscolaridade() {
    return escolaridade;
  }

  public void setEscolaridade(String escolaridade) {
    this.escolaridade = escolaridade;
  }

  public LocalDate getDt_nascimento() {
    return dt_nascimento;
  }

  public void setDt_nascimento(LocalDate dt_nascimento) {
    this.dt_nascimento = dt_nascimento;
  }

  public String getEstado_civil() {
    return estado_civil;
  }

  public void setEstado_civil(String estado_civil) {
    this.estado_civil = estado_civil;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getBiografia() {
    return biografia;
  }

  public void setBiografia(String biografia) {
    this.biografia = biografia;
  }
}
