package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.dto.request.LoginDto;
import cufa.conecta.com.br.application.dto.request.usuario.UsuarioRequestDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioResponseDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioTokenDto;
import cufa.conecta.com.br.domain.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    private UsuarioService service;
    private UsuarioController controller;

    @BeforeEach
    public void setup() {
        service = mock(UsuarioService.class);
        controller = new UsuarioController(service);
    }

    @Test
    public void deveChamarCadastrarUsuario() {
        UsuarioRequestDto requestDto = new UsuarioRequestDto("Nome Teste", "email@teste.com", "senha123");

        controller.cadastrarUsuario(requestDto);

        verify(service).cadastrarUsuario(requestDto);
    }

    @Test
    public void deveRetornarTokenNoLogin() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("email@teste.com");
        loginDto.setSenha("senha123");

        UsuarioTokenDto tokenDto = new UsuarioTokenDto("Nome Teste", "email@teste.com", "token123");
        when(service.login(loginDto)).thenReturn(tokenDto);

        UsuarioTokenDto resposta = controller.login(loginDto);

        assertNotNull(resposta);
        assertEquals("Nome Teste", resposta.getNome());
        assertEquals("email@teste.com", resposta.getEmail());
        assertEquals("token123", resposta.getToken());
    }

    @Test
    public void listarUsuarios_deveRetornar204_quandoListaVazia() {
        when(service.listarTodos()).thenReturn(Collections.emptyList());

        ResponseEntity<List<UsuarioResponseDto>> response = controller.listarUsuarios();

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void listarUsuarios_deveRetornar200_eListaUsuarios() {
        UsuarioResponseDto usuario1 = new UsuarioResponseDto("Nome 1", "email1@teste.com");
        UsuarioResponseDto usuario2 = new UsuarioResponseDto("Nome 2", "email2@teste.com");
        List<UsuarioResponseDto> usuarios = Arrays.asList(usuario1, usuario2);

        when(service.listarTodos()).thenReturn(usuarios);

        ResponseEntity<List<UsuarioResponseDto>> response = controller.listarUsuarios();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Nome 1", response.getBody().get(0).getNome());
        assertEquals("email2@teste.com", response.getBody().get(1).getEmail());
    }

    @Test
    public void atualizarUsuario_deveChamarServicoComUsuarioData() {
        UsuarioRequestDto requestDto = new UsuarioRequestDto("Nome Atualizado", "email@atualizado.com", "novaSenha");

        Long id = 10L;

        controller.atualizarUsuario(id, requestDto);

        verify(service).atualizarUsuario(argThat(usuarioData ->
                usuarioData.getId().equals(id) &&
                        usuarioData.getNome().equals("Nome Atualizado") &&
                        usuarioData.getEmail().equals("email@atualizado.com") &&
                        usuarioData.getSenha().equals("novaSenha")
        ));
    }

    @Test
    public void deletarUsuario_deveChamarServicoComId() {
        Long id = 5L;

        controller.deletarUsuario(id);

        verify(service).deletar(id);
    }
}
