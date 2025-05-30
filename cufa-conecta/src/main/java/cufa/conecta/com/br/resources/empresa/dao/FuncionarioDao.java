package cufa.conecta.com.br.resources.empresa.dao;

import cufa.conecta.com.br.resources.empresa.entity.FuncionarioEntity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioDao extends JpaRepository<FuncionarioEntity, Long> {
  Optional<FuncionarioEntity> findByEmail(String email);
  List<FuncionarioEntity> findByEmpresaId(Long empresaId);

}
