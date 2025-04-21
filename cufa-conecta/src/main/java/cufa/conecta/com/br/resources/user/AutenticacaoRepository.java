package cufa.conecta.com.br.resources.user;

import cufa.conecta.com.br.application.exception.NotFoundException;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AutenticacaoRepository implements UserDetailsService {
    private final UsuarioDao dao;

    public AutenticacaoRepository(UsuarioDao dao) { this.dao = dao; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioOpt = dao.findByEmail(username);

        if (usuarioOpt.isEmpty()) {
            throw new NotFoundException("O usuário inserido não foi encontrado");
        }

        UsuarioEntity usuario = usuarioOpt.get();

        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }
}
