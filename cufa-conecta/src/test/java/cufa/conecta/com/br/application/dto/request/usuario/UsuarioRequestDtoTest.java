package cufa.conecta.com.br.application.dto.request.usuario;

import static org.junit.jupiter.api.Assertions.*;

import cufa.conecta.com.br.application.exception.BadRequestException;
import cufa.conecta.com.br.model.UsuarioData;
import org.junit.jupiter.api.Test;

public class UsuarioRequestDtoTest {

    @Test
    public void toModel_deveRetornarUsuarioData_quandoCamposValidos() {
        UsuarioRequestDto dto = new UsuarioRequestDto("Nome Teste", "email@teste.com","senha123");

        UsuarioData usuarioData = dto.toModel();

        assertNotNull(usuarioData);
        assertEquals("Nome Teste", usuarioData.getNome());
        assertEquals("email@teste.com", usuarioData.getEmail());
        assertEquals("senha123", usuarioData.getSenha());
    }

    @Test
    public void toModel_deveLancarBadRequestException_quandoNomeForNulo() {
        UsuarioRequestDto dto = new UsuarioRequestDto(null, "email@teste.com", "senha123");

        BadRequestException exception = assertThrows(BadRequestException.class, dto::toModel);
        assertEquals("Nome, email, ou senha não podem ser nulos", exception.getMessage());
    }

    @Test
    public void toModel_deveLancarBadRequestException_quandoEmailForNulo() {
        UsuarioRequestDto dto = new UsuarioRequestDto("Nome", null, "senha123");

        BadRequestException exception = assertThrows(BadRequestException.class, dto::toModel);
        assertEquals("Nome, email, ou senha não podem ser nulos", exception.getMessage());
    }

    @Test
    public void toModel_deveLancarBadRequestException_quandoSenhaForNulo() {
        UsuarioRequestDto dto = new UsuarioRequestDto("Nome", "email@teste.com", null);

        BadRequestException exception = assertThrows(BadRequestException.class, dto::toModel);
        assertEquals("Nome, email, ou senha não podem ser nulos", exception.getMessage());
    }
}
