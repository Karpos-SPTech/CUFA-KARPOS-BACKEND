package cufa.conecta.com.br.resources.user;

import cufa.conecta.com.br.resources.user.dao.CandidaturaDao;
import cufa.conecta.com.br.resources.user.entity.CandidaturaEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CandidaturaRepository {
  private final CandidaturaDao dao;

  public CandidaturaRepository(CandidaturaDao dao) {
    this.dao = dao;
  }

      public CandidaturaEntity criarCandidatura(CandidaturaEntity candidatura) {
          return dao.save(candidatura);
      }

}
