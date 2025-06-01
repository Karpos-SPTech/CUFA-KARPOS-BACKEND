package cufa.conecta.com.br.domain.enums;

public enum Cargo {
  FUNCIONARIO,
  ADMINISTRADOR;

  public static Cargo fromString(String value) {
    try {
      return Cargo.valueOf(value.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Cargo inválido. Use FUNCIONARIO ou ADMINISTRADOR.");
    }
  }
}
