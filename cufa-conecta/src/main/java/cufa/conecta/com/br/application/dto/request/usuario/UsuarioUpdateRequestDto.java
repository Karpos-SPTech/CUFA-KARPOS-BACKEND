package cufa.conecta.com.br.application.dto.request.usuario;

import cufa.conecta.com.br.application.exception.BadRequestException;
import cufa.conecta.com.br.model.UsuarioData;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class UsuarioUpdateRequestDto {
  @NotBlank String nome;
  String cpf;
  String telefone;
  String escolaridade;
  LocalDate dtNascimento;
  String estadoCivil;
  String estado;
  String cidade;
  String biografia;

  public UsuarioUpdateRequestDto(
      String nome,
      String cpf,
      String telefone,
      String escolaridade,
      LocalDate dtNascimento,
      String estadoCivil,
      String estado,
      String cidade,
      String biografia) {
    this.nome = nome;
    this.cpf = cpf;
    this.telefone = telefone;
    this.escolaridade = escolaridade;
    this.dtNascimento = dtNascimento;
    this.estadoCivil = estadoCivil;
    this.estado = estado;
    this.cidade = cidade;
    this.biografia = biografia;
  }

  public UsuarioData toModel() {
    if (nome == null
        || cpf == null
        || telefone == null
        || escolaridade == null
        || dtNascimento == null
        || estadoCivil == null
        || estado == null
        || cidade == null
        || biografia == null) {
      throw new BadRequestException("Todos os campos devem ser preenchidos");
    }

    return new UsuarioData(
        nome, cpf, telefone, escolaridade, dtNascimento, estadoCivil, estado, cidade, biografia);
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public String getTelefone() {
    return telefone;
  }

  public String getEscolaridade() {
    return escolaridade;
  }

  public LocalDate getDtNascimento() {
    return dtNascimento;
  }

  public String getEstadoCivil() {
    return estadoCivil;
  }

  public String getEstado() {
    return estado;
  }

  public String getCidade() {
    return cidade;
  }

  public String getBiografia() {
    return biografia;
  }
}
