package cufa.conecta.com.br.resources;

import cufa.conecta.com.br.application.exception.NotFoundException;
import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
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
    private final UsuarioDao usuarioDao;
    private final EmpresaDao empresaDao;

    public AutenticacaoRepository(UsuarioDao usuarioDao, EmpresaDao empresaDao) {
        this.usuarioDao = usuarioDao;
        this.empresaDao = empresaDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioOpt = usuarioDao.findByEmail(username);

        if (usuarioOpt.isPresent()) {
            UsuarioEntity usuario = usuarioOpt.get();
            return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
        }

        Optional<EmpresaEntity> empresaOpt = empresaDao.findByEmail(username);

        if (empresaOpt.isPresent()) {
            EmpresaEntity empresa = empresaOpt.get();
            return new User(empresa.getEmail(), empresa.getSenha(), new ArrayList<>());
        }

        throw new NotFoundException("Usuário ou empresa com este e-mail não foi encontrado.");
    }
}
