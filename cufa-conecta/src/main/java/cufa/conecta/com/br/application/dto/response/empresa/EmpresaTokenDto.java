package cufa.conecta.com.br.application.dto.response.empresa;

public class EmpresaTokenDto {

  private String nome;
  private String email;
  private String token;

  public EmpresaTokenDto(String nome, String email) {
    this.nome = nome;
    this.email = email;
  }

  public EmpresaTokenDto(String nome, String email, String token) {
    this.nome = nome;
    this.email = email;
    this.token = token;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getToken() {
    return token;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
