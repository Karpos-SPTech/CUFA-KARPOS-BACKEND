package cufa.conecta.com.br.application.dto.request.empresa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class FuncionarioRequestDto {
  @NotBlank(message = "O campo do nome não pode ser nulo, vazio ou branco")
  public String nome;

  @Email
  public String email;

  @NotBlank(message = "O campo da senha não pode ser nulo, vazio ou branco")
  public String senha;

  @NotBlank(message = "O campo do cargo não pode ser nulo, vazio ou branco")
  public String cargo;
}
