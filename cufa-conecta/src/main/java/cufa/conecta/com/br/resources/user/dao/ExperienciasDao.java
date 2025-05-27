package cufa.conecta.com.br.resources.user.dao;

import cufa.conecta.com.br.resources.user.entity.ExperienciasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienciasDao extends JpaRepository<ExperienciasEntity, Long> {
    List<ExperienciasEntity> findByFkUsuario(Long fkUsuario);
}
