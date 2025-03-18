package cufa.conecta.com.br.resources.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cadastro_usuario")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String nome;

    @Column
    String email;

    @Column
    String senha;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() { return senha; }

    public void setNome(String nome) { this.nome = nome; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
