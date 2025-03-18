package cufa.conecta.com.br.resources.user.dao;

import cufa.conecta.com.br.resources.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer>, PagingAndSortingRepository<UserEntity,Integer> {
    UserEntity findByEmail(String email);
}