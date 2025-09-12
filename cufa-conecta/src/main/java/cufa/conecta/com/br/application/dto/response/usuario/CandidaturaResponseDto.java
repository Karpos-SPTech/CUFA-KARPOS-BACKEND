package cufa.conecta.com.br.application.dto.response.usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CandidaturaResponseDto {
    public String titulo;
    public String nomeEmpresa;
    public String tipoContrato;
    public LocalDateTime dtPublicacao;
    public LocalDateTime dtExpiracao;
    public int qtdCandidatos;
    public List<CandidatoDto> candidatos;

    public CandidaturaResponseDto(String titulo, String nomeEmpresa, String tipoContrato, LocalDateTime dtPublicacao, LocalDateTime dtExpiracao, int qtdCandidatos, List<CandidatoDto> candidatos) {
        this.titulo = titulo;
        this.nomeEmpresa = nomeEmpresa;
        this.tipoContrato = tipoContrato;
        this.dtPublicacao = dtPublicacao;
        this.dtExpiracao = dtExpiracao;
        this.qtdCandidatos = qtdCandidatos;
        this.candidatos = candidatos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public LocalDateTime getDtPublicacao() {
        return dtPublicacao;
    }

    public void setDtPublicacao(LocalDateTime dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
    }

    public LocalDateTime getDtExpiracao() {
        return dtExpiracao;
    }

    public void setDtExpiracao(LocalDateTime dtExpiracao) {
        this.dtExpiracao = dtExpiracao;
    }

    public int getQtdCandidatos() {
        return qtdCandidatos;
    }

    public void setQtdCandidatos(int qtdCandidatos) {
        this.qtdCandidatos = qtdCandidatos;
    }

    public List<CandidatoDto> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<CandidatoDto> candidatos) {
        this.candidatos = candidatos;
    }
}
