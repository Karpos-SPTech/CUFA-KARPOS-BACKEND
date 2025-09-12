package cufa.conecta.com.br.application.dto.request.usuario;

import cufa.conecta.com.br.application.exception.BadRequestException;
import cufa.conecta.com.br.model.UsuarioData;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioRequestDto {
  @NotBlank String nome;
  @Email String email;
  @NotBlank String senha;

  public UsuarioRequestDto(String nome, String email, String senha) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }

  public UsuarioData toModel() {
    if (nome == null || email == null || senha == null) {
      throw new BadRequestException("Todos os campos devem ser preenchidos");
    }
    return new UsuarioData(nome, email, senha);
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
}
