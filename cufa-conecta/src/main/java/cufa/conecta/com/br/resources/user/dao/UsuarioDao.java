package cufa.conecta.com.br.resources.user.dao;

import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioDao extends JpaRepository<UsuarioEntity, Long>, PagingAndSortingRepository<UsuarioEntity,Long> {
    Optional<UsuarioEntity> findByEmail(String email);
}