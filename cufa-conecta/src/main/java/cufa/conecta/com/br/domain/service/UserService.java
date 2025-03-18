package cufa.conecta.com.br.domain.service;

import cufa.conecta.com.br.model.UserData;
import cufa.conecta.com.br.resources.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void cadastrarUsuario(UserData userDto) {
        repository.cadastrarUsuario(userDto);
    }

    public List<UserData> buscarUsuarios(Integer pagina, Integer tamanho) {
       return repository.buscarUsuarios(pagina, tamanho);
    }

    public void atualizarUsuario(UserData usuario) {
        repository.atualizar(usuario);
    }

    public void deletar(Integer id) {
        repository.deletar(id);
    }
}
