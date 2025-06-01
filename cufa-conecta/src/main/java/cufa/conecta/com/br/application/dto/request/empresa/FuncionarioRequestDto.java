package cufa.conecta.com.br.application.dto.request.empresa;

import cufa.conecta.com.br.application.exception.EmpresaBadRequest;
import cufa.conecta.com.br.model.FuncionarioData;

public class FuncionarioRequestDto {
  public String nome;
  public String email;
  public String senha;
  public String cargo;

  public FuncionarioData toModel() {
    if (nome == null || email == null || senha == null || cargo == null) {
      throw new EmpresaBadRequest("Os campos inseridos não podem ser nulos");
    }
    return new FuncionarioData(nome, email, senha, cargo);
  }
}
