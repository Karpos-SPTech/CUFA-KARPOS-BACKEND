package cufa.conecta.com.br.application.dto.request.usuario;

import cufa.conecta.com.br.application.exception.BadRequestException;
import cufa.conecta.com.br.model.PerfilUsuarioData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UsuarioPerfilRequestDto {
    @NotBlank
    String cpf;
    @NotBlank
    String telefone;
    @NotBlank
    String escolaridade;
    @NotNull
    LocalDate dataNascimento;
    @NotBlank
    String estadoCivil;
    @NotBlank
    String estado;
    @NotBlank
    String cidade;
    @NotBlank
    String biografia;

    public UsuarioPerfilRequestDto(String cpf, String telefone, String escolaridade, LocalDate dataNascimento, String estadoCivil, String estado, String cidade, String biografia) {
        this.cpf = cpf;
        this.telefone = telefone;
        this.escolaridade = escolaridade;
        this.dataNascimento = dataNascimento;
        this.estadoCivil = estadoCivil;
        this.estado = estado;
        this.cidade = cidade;
        this.biografia = biografia;
    }

    public PerfilUsuarioData toModel() {
        if (cpf == null || telefone == null || escolaridade == null || dataNascimento == null ||
                estadoCivil == null || estado == null || cidade == null || biografia == null) {
            throw new BadRequestException("Todos os campos do perfil devem ser preenchidos");
        }

        return new PerfilUsuarioData(
                cpf,
                telefone,
                escolaridade,
                dataNascimento,
                estadoCivil,
                estado,
                cidade,
                biografia,
                toModel().getFkUsuario()
        );
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBiografia() {
        return biografia;
    }
}
