package cufa.conecta.com.br.resources.empresa.dao;

import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaDao extends JpaRepository<EmpresaEntity, Integer>, PagingAndSortingRepository<EmpresaEntity,Integer> {
    EmpresaEntity findByEmail(String email);
}
