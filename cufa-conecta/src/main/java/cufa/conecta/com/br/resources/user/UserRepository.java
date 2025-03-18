package cufa.conecta.com.br.resources.user;

import cufa.conecta.com.br.model.UserData;
import cufa.conecta.com.br.resources.user.dao.UserDao;
import cufa.conecta.com.br.resources.user.entity.UserEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class UserRepository{

    //-------------- Realizando a injeção de depêndencia da UserDao -----------

    private final UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    //------------------------- Métodos do usuário -----------------------------

    public void cadastrarUsuario(UserData userDto) {
        UserEntity userEntity = toEntity(userDto);

        if (userDao.findByEmail(userEntity.getEmail()) != null) {
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }

        userDao.save(userEntity);
    }

    public List<UserData> buscarUsuarios(Integer pagina, Integer tamanho) {
        List<UserEntity> listaDeEntityDoUsuario = userDao.findAll(
                PageRequest.of(pagina, tamanho)
        ).toList();

        List<UserData> listaDeUsuarios = new ArrayList<>();

        for (UserEntity usuario : listaDeEntityDoUsuario) {
            UserData userData = new UserData(usuario.getNome(), usuario.getEmail(),usuario.getSenha());
            listaDeUsuarios.add(userData);
        }
        return listaDeUsuarios;
    }

    public void atualizar(UserData usuario) {
        UserEntity usuarioExistente = userDao.findById(usuario.getId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setSenha(usuario.getSenha());

        userDao.save(usuarioExistente);
    }

    public void deletar(Integer id) {
        UserEntity usuario = userDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        userDao.delete(usuario);
    }

    // ------------------ Método privado para conversão de dados --------------

    private UserEntity toEntity(UserData userData) {
        UserEntity entity = new UserEntity();
        entity.setNome(userData.getNome());
        entity.setEmail(userData.getEmail());
        entity.setSenha(userData.getSenha());

        return entity;
    }
}

