package cufa.conecta.com.br.resources.user.dao;

import cufa.conecta.com.br.resources.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Integer> {
}