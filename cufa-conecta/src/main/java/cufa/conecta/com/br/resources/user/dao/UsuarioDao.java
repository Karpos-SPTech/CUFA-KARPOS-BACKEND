package cufa.conecta.com.br.resources.user.dao;

import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<UsuarioEntity, Long> {
  Optional<UsuarioEntity> findByEmail(String email);

  @Query("SELECT c.usuario FROM CandidaturaEntity c WHERE c.publicacao.id = :vagaId")
  List<UsuarioEntity> findUsuariosByPublicacaoId(@Param("vagaId") Long vagaId);
}
