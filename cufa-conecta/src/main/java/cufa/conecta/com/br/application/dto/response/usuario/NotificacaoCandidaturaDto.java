package cufa.conecta.com.br.application.dto.response.usuario;

public class NotificacaoCandidaturaDto {
    public String nomeCandidato;
    public String tituloVaga;

    public NotificacaoCandidaturaDto() {}

    public NotificacaoCandidaturaDto(String nomeCandidato, String tituloVaga) {
        this.nomeCandidato = nomeCandidato;
        this.tituloVaga = tituloVaga;
    }

    public String getNomeCandidato() {
        return nomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        this.nomeCandidato = nomeCandidato;
    }

    public String getTituloVaga() {
        return tituloVaga;
    }

    public void setTituloVaga(String tituloVaga) {
        this.tituloVaga = tituloVaga;
    }

}
