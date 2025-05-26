package cufa.conecta.com.br.application.dto.request.usuario;

import cufa.conecta.com.br.application.exception.BadRequestException;
import cufa.conecta.com.br.model.ExperienciaData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ExperienciaRequestDto {
    @NotBlank
    String cargo;
    @NotBlank
    String empresa;
    @NotBlank
    String cidade;
    @NotBlank
    String estado;
    @NotNull
    LocalDate dtInicio;
    @NotNull
    LocalDate dtFim;

    public ExperienciaRequestDto(String cargo, String empresa, String cidade, String estado, LocalDate dtInicio, LocalDate dtFim) {
        this.cargo = cargo;
        this.empresa = empresa;
        this.cidade = cidade;
        this.estado = estado;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
    }

    public ExperienciaData toModel(){
        if(cargo == null || empresa == null || cidade == null || estado == null || dtInicio == null || dtFim == null){
            throw new BadRequestException("Campos não podem ser nulos");
        }
        return new ExperienciaData(
                cargo,
                empresa,
                cidade,
                estado,
                dtInicio,
                dtFim
        );
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
}
