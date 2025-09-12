package cufa.conecta.com.br.resources.empresa.dao;

import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaDao extends JpaRepository<EmpresaEntity, Long> {
  Optional<EmpresaEntity> findByEmail(String email);
}
