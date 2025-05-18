package cufa.conecta.com.br.domain.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cufa.conecta.com.br.application.dto.request.LoginDto;
import cufa.conecta.com.br.application.dto.request.usuario.UsuarioRequestDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioResponseDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioTokenDto;
import cufa.conecta.com.br.model.UsuarioData;
import cufa.conecta.com.br.resources.user.UsuarioRepository;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(usuarioService, "observers", Collections.emptyList());
    }

    @Test
    public void testarCadastrarUsuario() {
        UsuarioRequestDto userDto = new UsuarioRequestDto("Alezinho", "alezinho@example.com", "123456");

        when(passwordEncoder.encode("123456")).thenReturn("senhaCripto");

        usuarioService.cadastrarUsuario(userDto);

        ArgumentCaptor<UsuarioData> captor = ArgumentCaptor.forClass(UsuarioData.class);
        verify(repository).cadastrarUsuario(captor.capture());

        UsuarioData usuarioSalvo = captor.getValue();

        assertEquals("Alezinho", usuarioSalvo.getNome());
        assertEquals("alezinho@example.com", usuarioSalvo.getEmail());
        assertEquals("senhaCripto", usuarioSalvo.getSenha());
    }

    @Test
    public void testLoginSucesso() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("teste@exemplo.com");
        loginDto.setSenha("senha123");

        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto(
                "Nome Teste",
                "teste@exemplo.com",
                "token123"
        );

        when(repository.autenticar(any(UsuarioData.class))).thenReturn(usuarioTokenDto);

        UsuarioTokenDto resultado = usuarioService.login(loginDto);

        assertNotNull(resultado);
        assertEquals("Nome Teste", resultado.getNome());
        assertEquals("teste@exemplo.com", resultado.getEmail());
        assertEquals("token123", resultado.getToken());

        verify(repository, times(1)).autenticar(any(UsuarioData.class));
    }

    @Test
    public void testListarTodos_RetornaListaDeDtos() {
        // Arrange - prepara dados mockados
        UsuarioEntity usuario1 = new UsuarioEntity();
        usuario1.setNome("Usuario 1");
        usuario1.setEmail("usuario1@email.com");

        UsuarioEntity usuario2 = new UsuarioEntity();
        usuario2.setNome("Usuario 2");
        usuario2.setEmail("usuario2@email.com");

        List<UsuarioEntity> listaUsuarios = Arrays.asList(usuario1, usuario2);

        when(repository.listarTodos()).thenReturn(listaUsuarios);

        List<UsuarioResponseDto> resultado = usuarioService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        assertEquals("Usuario 1", resultado.get(0).getNome());
        assertEquals("usuario1@email.com", resultado.get(0).getEmail());

        assertEquals("Usuario 2", resultado.get(1).getNome());
        assertEquals("usuario2@email.com", resultado.get(1).getEmail());

        verify(repository, times(1)).listarTodos();
    }

    @Test
    public void testAtualizarUsuario_DeveChamarRepositorioComUsuario() {
        UsuarioData usuario = new UsuarioData();
        usuario.setNome("Nome Atualizado");
        usuario.setEmail("email@atualizado.com");

        usuarioService.atualizarUsuario(usuario);

        verify(repository, times(1)).atualizar(usuario);
    }

    @Test
    public void testDeletar_DeveChamarRepositorioComId() {
        Long idParaDeletar = 123L;

        usuarioService.deletar(idParaDeletar);

        verify(repository, times(1)).deletar(idParaDeletar);
    }
}
