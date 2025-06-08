package cufa.conecta.com.br.resources.user.dao;

import cufa.conecta.com.br.resources.user.entity.CandidaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaDao extends JpaRepository<CandidaturaEntity, Long> {}
