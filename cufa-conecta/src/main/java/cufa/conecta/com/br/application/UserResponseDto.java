package cufa.conecta.com.br.application;

import cufa.conecta.com.br.model.UserData;

public class UserResponseDto {
    String nome;
    String email;
    String senha;

    public UserResponseDto(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public static UserResponseDto of(UserData userData) {
        return new UserResponseDto(userData.getNome(), userData.getEmail(), userData.getSenha());
    }
}
