package cufa.conecta.com.br.resources.user;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import cufa.conecta.com.br.application.dto.response.usuario.UsuarioTokenDto;
import cufa.conecta.com.br.config.GerenciadorTokenJwt;
import cufa.conecta.com.br.model.UsuarioData;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class UsuarioRepositoryTest {

    @Mock
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UsuarioDao usuarioDao;

    @InjectMocks
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void autenticar_deveRetornarTokenDto() {
        UsuarioData usuarioData = new UsuarioData();
        usuarioData.setEmail("email@teste.com");
        usuarioData.setSenha("123");

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome("Nome");
        usuarioEntity.setEmail("email@teste.com");
        usuarioEntity.setSenha("123");

        Authentication authMock = mock(Authentication.class);
        String tokenFake = "tokenFake";

        when(usuarioDao.findByEmail(anyString())).thenReturn(Optional.of(usuarioEntity));
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authMock);
        when(gerenciadorTokenJwt.generateToken(any())).thenReturn(tokenFake);

        UsuarioTokenDto resultado = usuarioRepository.autenticar(usuarioData);

        assertNotNull(resultado);
        assertEquals("Nome", resultado.getNome());
        assertEquals("email@teste.com", resultado.getEmail());
        assertEquals(tokenFake, resultado.getToken());
    }

    @Test
    public void listarTodos_deveRetornarListaUsuarios() {
        UsuarioEntity u1 = new UsuarioEntity();
        u1.setNome("A");
        u1.setEmail("a@email.com");
        UsuarioEntity u2 = new UsuarioEntity();
        u2.setNome("B");
        u2.setEmail("b@email.com");

        when(usuarioDao.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<UsuarioEntity> lista = usuarioRepository.listarTodos();

        assertEquals(2, lista.size());
        assertEquals("A", lista.get(0).getNome());
    }

    @Test
    public void atualizar_deveAlterarUsuarioExistente() {
        UsuarioData data = new UsuarioData();
        data.setId(1L);
        data.setNome("NovoNome");
        data.setEmail("novo@email.com");
        data.setSenha("novaSenha");

        UsuarioEntity existente = new UsuarioEntity();
        existente.setId(1L);
        existente.setNome("AntigoNome");
        existente.setEmail("antigo@email.com");
        existente.setSenha("senhaAntiga");

        when(usuarioDao.findById(1L)).thenReturn(Optional.of(existente));
        when(usuarioDao.save(any())).thenReturn(existente);

        usuarioRepository.atualizar(data);

        assertEquals("NovoNome", existente.getNome());
        assertEquals("novo@email.com", existente.getEmail());
        assertEquals("novaSenha", existente.getSenha());
    }

    @Test
    public void deletar_deveChamarDelete() {
        Long id = 1L;
        UsuarioEntity existente = new UsuarioEntity();
        existente.setId(id);

        when(usuarioDao.findById(id)).thenReturn(Optional.of(existente));
        doNothing().when(usuarioDao).delete(existente);

        usuarioRepository.deletar(id);

        verify(usuarioDao, times(1)).delete(existente);
    }
}
