package cufa.conecta.com.br.application.dto.response.usuario;

import java.time.LocalDate;

public class UsuarioResponseDto {
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String escolaridade;
    private LocalDate dtNascimento;
    private String estadoCivil;
    private String estado;
    private String cidade;
    private String biografia;

    public UsuarioResponseDto(String nome, String email, String cpf, String telefone, String escolaridade, LocalDate dtNascimento, String estadoCivil, String estado, String cidade, String biografia) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.escolaridade = escolaridade;
        this.dtNascimento = dtNascimento;
        this.estadoCivil = estadoCivil;
        this.estado = estado;
        this.cidade = cidade;
        this.biografia = biografia;
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

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
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
