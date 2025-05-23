package cufa.conecta.com.br.application.dto.response.empresa;

public class EmpresaResponseDto {
  Long id;
  String nome;
  String email;
  String cep;
  String numero;
  String endereco;
  String cnpj;
  String area;

  public EmpresaResponseDto(
      Long id,
      String nome,
      String email,
      String cep,
      String numero,
      String endereco,
      String cnpj,
      String area) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cep = cep;
    this.numero = numero;
    this.endereco = endereco;
    this.cnpj = cnpj;
    this.area = area;
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

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getCnpj() {
    return cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }
}
