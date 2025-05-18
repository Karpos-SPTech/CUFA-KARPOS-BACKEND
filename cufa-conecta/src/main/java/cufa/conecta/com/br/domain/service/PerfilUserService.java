package cufa.conecta.com.br.domain.service;

import cufa.conecta.com.br.application.dto.request.usuario.UsuarioPerfilRequestDto;
import cufa.conecta.com.br.model.PerfilUsuarioData;
import cufa.conecta.com.br.resources.user.PerfilUsuarioRepository;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PerfilUserService {
    private final PerfilUsuarioRepository perfilRepository;
    private final UsuarioDao usuarioDao; // Usa DAO

    public PerfilUserService(PerfilUsuarioRepository perfilRepository, UsuarioDao usuarioDao) {
        this.perfilRepository = perfilRepository;
        this.usuarioDao = usuarioDao;
    }

    public void criarPerfilUsuario(UsuarioPerfilRequestDto userDto, String email) {
        UsuarioEntity usuario = usuarioDao.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com email: " + email));

        PerfilUsuarioData perfilUsuarioData = new PerfilUsuarioData(
                userDto.getCpf(),
                userDto.getTelefone(),
                userDto.getEscolaridade(),
                userDto.getDataNascimento(),
                userDto.getEstadoCivil(),
                userDto.getEstado(),
                userDto.getCidade(),
                userDto.getBiografia(),
                usuario.getId()
        );

        perfilRepository.criarPerfilUsuario(perfilUsuarioData);
    }
}
