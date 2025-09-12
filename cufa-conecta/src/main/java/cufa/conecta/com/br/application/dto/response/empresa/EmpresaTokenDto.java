package cufa.conecta.com.br.application.dto.response.empresa;

public class EmpresaTokenDto {

  private Long id;
  private String nome;
  private String email;
  private final String token;

  public EmpresaTokenDto(Long id, String nome, String email, String token) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.token = token;
  }

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getToken() { return token; }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
