package cufa.conecta.com.br.domain.service;

import cufa.conecta.com.br.application.dto.request.UsuarioLoginDto;
import cufa.conecta.com.br.application.dto.response.UsuarioResponseDto;
import cufa.conecta.com.br.application.dto.response.UsuarioTokenDto;
import cufa.conecta.com.br.application.dto.request.UsuarioRequestDto;
import cufa.conecta.com.br.model.UsuarioData;
import cufa.conecta.com.br.resources.user.UsuarioRepository;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrarUsuario(UsuarioRequestDto userDto) {
        String senhaCriptografada = passwordEncoder.encode(userDto.getSenha());

        UsuarioData usuarioData = new UsuarioData(
                userDto.getNome(),
                userDto.getEmail(),
                senhaCriptografada
        );

        repository.cadastrarUsuario(usuarioData);
    }

    public UsuarioTokenDto login(UsuarioLoginDto usuarioLoginDto) {
        UsuarioData usuarioData = new UsuarioData();

        usuarioData.setEmail(usuarioLoginDto.getEmail());
        usuarioData.setSenha(usuarioLoginDto.getSenha());

        return repository.autenticar(usuarioData);
    }

    public List<UsuarioResponseDto> listarTodos() {
        List<UsuarioEntity> usuariosEncontrados = repository.listarTodos();
        return usuariosEncontrados.stream()
                .map(usuario -> new UsuarioResponseDto(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail())
                )
                .collect(Collectors.toList());
    }

    public void atualizarUsuario(UsuarioData usuario) {
        repository.atualizar(usuario);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
