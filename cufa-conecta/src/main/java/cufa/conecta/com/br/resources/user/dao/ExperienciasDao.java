package cufa.conecta.com.br.resources.user.dao;

import cufa.conecta.com.br.resources.user.entity.ExperienciasEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienciasDao extends JpaRepository<ExperienciasEntity, Long> {
  List<ExperienciasEntity> findByFkUsuario(Long fkUsuario);
}
