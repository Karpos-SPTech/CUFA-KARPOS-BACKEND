package cufa.conecta.com.br.domain.service.usuario;

import cufa.conecta.com.br.application.dto.request.LoginDto;
import cufa.conecta.com.br.application.dto.request.usuario.UsuarioRequestDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioResponseDto;
import cufa.conecta.com.br.application.dto.response.usuario.UsuarioTokenDto;
import cufa.conecta.com.br.domain.service.observer.CadastroObserver;
import cufa.conecta.com.br.model.UsuarioData;
import cufa.conecta.com.br.resources.user.UsuarioRepository;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioDao dao;
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final List<CadastroObserver> observers;

    public UsuarioService(UsuarioDao dao, UsuarioRepository repository, PasswordEncoder passwordEncoder, List<CadastroObserver> observers) {
        this.dao = dao;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.observers = observers;
    }

    public void cadastrarUsuario(UsuarioRequestDto userDto) {
        String senhaCriptografada = passwordEncoder.encode(userDto.getSenha());

        UsuarioData usuarioData =
                new UsuarioData(userDto.getNome(), userDto.getEmail(), senhaCriptografada);

        repository.cadastrarUsuario(usuarioData);

        for (CadastroObserver observer : observers) {
            observer.notificar(usuarioData);
        }
    }

    public UsuarioResponseDto mostrarDados(Long id) {
        return repository.mostrarDados(id);
    }

    public UsuarioTokenDto login(LoginDto usuarioLoginDto) {
        UsuarioData usuarioData = new UsuarioData();

        usuarioData.setEmail(usuarioLoginDto.getEmail());
        usuarioData.setSenha(usuarioLoginDto.getSenha());

        return repository.autenticar(usuarioData);
    }

    public List<UsuarioResponseDto> listarTodos() {
        List<UsuarioEntity> usuariosEncontrados = repository.listarTodos();

        return usuariosEncontrados.stream()
                .map(
                        usuario ->
                                new UsuarioResponseDto(
                                        usuario.getNome(),
                                        usuario.getCpf(),
                                        usuario.getTelefone(),
                                        usuario.getEscolaridade(),
                                        usuario.getDt_nascimento(),
                                        usuario.getEstado_civil(),
                                        usuario.getEstado(),
                                        usuario.getCidade(),
                                        usuario.getBiografia(),
                                        usuario.getCurriculoUrl()))
                .collect(Collectors.toList());
    }

    public void atualizarUsuario(UsuarioData usuario) {
        repository.atualizar(usuario);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }

    @Transactional
    public void atualizarCurriculoUrl(Long userId, String curriculoUrl) {
        UsuarioEntity usuario = dao.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para atualizar currículo."));
        usuario.setCurriculoUrl(curriculoUrl);
        dao.save(usuario);
    }
}
