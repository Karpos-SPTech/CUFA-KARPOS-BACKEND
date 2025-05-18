package cufa.conecta.com.br.resources.user.dao;

import cufa.conecta.com.br.resources.user.entity.PerfilUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilUsuarioDao extends JpaRepository<PerfilUsuarioEntity, Long> {
}
