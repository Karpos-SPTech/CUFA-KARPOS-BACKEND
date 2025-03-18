package cufa.conecta.com.br.model;

public class UserData {
    Integer id;
    String nome;
    String email;
    String senha;

    public UserData(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public String getSenha() { return senha;}

    public Integer getId() { return id;}

    public void setId(Integer id) { this.id = id; }
}


