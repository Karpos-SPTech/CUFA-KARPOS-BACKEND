package cufa.conecta.com.br.resources.empresa.dao;

import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface EmpresaDao extends JpaRepository<EmpresaEntity, Long> {
    Optional<EmpresaEntity> findByEmail(String email);
}
