package cufa.conecta.com.br.application.dto.response.usuario;

import java.time.LocalDate;

public class ExperienciaResponseDto {
    private String cargo;
    private String empresa;
    private String cidade;
    private String estado;
    private LocalDate dtInicio;
    private LocalDate dtFim;

    public ExperienciaResponseDto(String cargo, String empresa, String cidade, String estado, LocalDate dtInicio, LocalDate dtFim) {
        this.cargo = cargo;
        this.empresa = empresa;
        this.cidade = cidade;
        this.estado = estado;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
    }

    // Getters
    public String getCargo() {
        return cargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getDtInicio() {
        return dtInicio;
    }

    public LocalDate getDtFim() {
        return dtFim;
    }
}
