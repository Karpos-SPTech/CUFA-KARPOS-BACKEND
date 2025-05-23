package cufa.conecta.com.br.resources.empresa;

import cufa.conecta.com.br.resources.empresa.dao.PublicacaoDao;
import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PublicacaoRepository {

  private final PublicacaoDao publicacaoDao;

  public PublicacaoRepository(PublicacaoDao dao) {
    this.publicacaoDao = dao;
  }

  public PublicacaoEntity save(PublicacaoEntity publicacao) {
    return publicacaoDao.save(publicacao);
  }
}
