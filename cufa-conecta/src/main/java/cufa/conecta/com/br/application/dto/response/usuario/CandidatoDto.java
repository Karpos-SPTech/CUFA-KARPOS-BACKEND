package cufa.conecta.com.br.application.dto.response.usuario;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class CandidatoDto {
    public String nome;
    public int idade;
    public String biografia;
    public String email;
    public String telefone;
    public String escolaridade;
    public String curriculoUrl;
    public List<ExperienciaResponseDto> experiencias;

    public CandidatoDto(String nome, int idade, String biografia, String email, String telefone, String escolaridade, String curriculoUrl, List<ExperienciaResponseDto> experiencias) {
        this.nome = nome;
        this.idade = idade;
        this.biografia = biografia;
        this.email = email;
        this.telefone = telefone;
        this.escolaridade = escolaridade;
        this.curriculoUrl = curriculoUrl;
        this.experiencias = experiencias;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCurriculoUrl() {
        return curriculoUrl;
    }

    public void setCurriculoUrl(String curriculoUrl) {
        this.curriculoUrl = curriculoUrl;
    }

    public List<ExperienciaResponseDto> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<ExperienciaResponseDto> experiencias) {
        this.experiencias = experiencias;
    }
}