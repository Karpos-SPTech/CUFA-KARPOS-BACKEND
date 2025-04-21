package cufa.conecta.com.br.resources.user.entity;

import jakarta.persistence.*;

@Entity(name = "cadastro_usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String nome;

    @Column
    String email;

    @Column
    String senha;

    public Long getId() { return id; }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() { return senha; }

    public void setId(Long id) { this.id = id; }

    public void setNome(String nome) { this.nome = nome; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
