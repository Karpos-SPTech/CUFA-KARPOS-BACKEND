package cufa.conecta.com.br.application.dto.response.empresa;

import cufa.conecta.com.br.domain.enums.Cargo;
import cufa.conecta.com.br.resources.empresa.entity.FuncionarioEntity;

public class FuncionarioResponseDto {
  public Long id;
  public String nome;
  public String email;
  public Cargo cargo;

  public FuncionarioResponseDto(FuncionarioEntity entity) {
    this.id = entity.getIdFuncionario();
    this.nome = entity.getNome();
    this.email = entity.getEmail();
    this.cargo = entity.getCargo();
  }
}
