package cufa.conecta.com.br.model;

public class EmpresaData {
  Long id;
  String nome;
  String email;
  String senha;
  String cep;
  String numero;
  String endereco;
  String cnpj;
  String area;

  public EmpresaData(
      String nome,
      String email,
      String senha,
      String cep,
      String numero,
      String endereco,
      String cnpj,
      String area) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.cep = cep;
    this.numero = numero;
    this.endereco = endereco;
    this.cnpj = cnpj;
    this.area = area;
  }

  public EmpresaData() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getSenha() {
    return senha;
  }

  public String getCep() {
    return cep;
  }

  public String getNumero() {
    return numero;
  }

  public String getEndereco() {
    return endereco;
  }

  public String getCnpj() {
    return cnpj;
  }

  public String getArea() {
    return area;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }
}
