package cufa.conecta.com.br.model;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class UsuarioData {
    Long id;
    String nome;
    String email;
    String senha;
    String cpf;
    String telefone;
    String escolaridade;
    LocalDate dtNascimento;
    String estado_civil;
    String estado;
    String cidade;
    String biografia;

    public UsuarioData(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioData(String nome, String cpf, String telefone, String escolaridade, LocalDate dtNascimento, String estado_civil, String estado, String cidade, String biografia) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.escolaridade = escolaridade;
        this.dtNascimento = dtNascimento;
        this.estado_civil = estado_civil;
        this.estado = estado;
        this.cidade = cidade;
        this.biografia = biografia;
    }

    public UsuarioData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}


