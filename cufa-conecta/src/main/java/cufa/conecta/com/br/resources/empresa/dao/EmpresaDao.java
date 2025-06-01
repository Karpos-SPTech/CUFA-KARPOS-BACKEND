package cufa.conecta.com.br.resources.empresa.dao;

import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaDao extends JpaRepository<EmpresaEntity, Long> {
  Optional<EmpresaEntity> findByEmail(String email);

  @Query(
      value = "SELECT COUNT(*) FROM cadastro_empresa e WHERE e.dtCadastro BETWEEN :inicio AND :fim",
      nativeQuery = true)
  long contarCadastrosUltimaSemana(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
