package cufa.conecta.com.br.model;

public class FuncionarioData {
  public Long id;
  public String nome;
  public String email;
  public String senha;
  public String cargo;

  public FuncionarioData(String nome, String email, String senha, String cargo) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.cargo = cargo;
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

  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
