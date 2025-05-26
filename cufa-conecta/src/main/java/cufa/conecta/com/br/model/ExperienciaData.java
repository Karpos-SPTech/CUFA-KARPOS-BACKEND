package cufa.conecta.com.br.model;

import java.time.LocalDate;

public class ExperienciaData {
    Long id;
    Long fkUsuario;
    String cargo;
    String empresa;
    String cidade;
    String estado;
    LocalDate dtInicio;
    LocalDate dtFim;

    public ExperienciaData(String cargo, String empresa, String cidade, String estado, LocalDate dtInicio, LocalDate dtFim, Long fkUsuario) {
        this.cargo = cargo;
        this.empresa = empresa;
        this.cidade = cidade;
        this.estado = estado;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.fkUsuario = fkUsuario;
    }

    public ExperienciaData(String cargo, String empresa, String cidade, String estado, LocalDate dtInicio, LocalDate dtFim) {
        this.cargo = cargo;
        this.empresa = empresa;
        this.cidade = cidade;
        this.estado = estado;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
    }

    public Long getId() {
        return id;
    }

    public Long getFkUsuario() {
        return fkUsuario;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setFkUsuario(Long fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDtInicio(LocalDate dtInicio) {
        this.dtInicio = dtInicio;
    }

    public void setDtFim(LocalDate dtFim) {
        this.dtFim = dtFim;
    }
}