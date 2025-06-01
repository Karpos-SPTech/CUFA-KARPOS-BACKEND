package cufa.conecta.com.br.application.dto.request.empresa;

import jakarta.validation.constraints.NotBlank;

public class BiografiaRequestDto {
  @NotBlank(message = "O campo da biografia não pode ser nulo, vazio ou branco")
  String biografia;

  public BiografiaRequestDto(String biografia) {
    this.biografia = biografia;
  }

  public String getBiografia() {
    return biografia;
  }
}
