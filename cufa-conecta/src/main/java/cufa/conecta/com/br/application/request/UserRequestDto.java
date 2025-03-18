package cufa.conecta.com.br.application.request;

import cufa.conecta.com.br.model.UserData;

public class UserRequestDto {
    String nome;
    String email;
    String senha;

    public UserRequestDto(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UserData toModel() {
        if (nome == null || email == null || senha == null) {
            throw new IllegalArgumentException("Nome, email, ou senha não podem ser nulos");
        }
        return new UserData(nome, email, senha);
    }

}
