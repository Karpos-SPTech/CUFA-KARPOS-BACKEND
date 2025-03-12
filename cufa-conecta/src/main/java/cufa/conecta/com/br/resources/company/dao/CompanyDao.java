package cufa.conecta.com.br.resources.company.dao;

import cufa.conecta.com.br.resources.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<CompanyEntity, Integer> {
}
