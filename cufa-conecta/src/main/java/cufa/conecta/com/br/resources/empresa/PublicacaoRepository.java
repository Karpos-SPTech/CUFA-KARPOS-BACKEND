package cufa.conecta.com.br.resources.empresa;

import cufa.conecta.com.br.resources.empresa.dao.PublicacaoDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PublicacaoRepository {

  private final PublicacaoDao publicacaoDao;

  public PublicacaoRepository(PublicacaoDao dao) {
    this.publicacaoDao = dao;
  }

  public PublicacaoEntity save(PublicacaoEntity publicacao) {
    return publicacaoDao.save(publicacao);
  }

  public List<PublicacaoEntity> buscarTodas() { return publicacaoDao.findAll(); }

  public List<PublicacaoEntity> buscarPorEmpresa(EmpresaEntity empresa) {
    return publicacaoDao.findAllByEmpresa(empresa);
  }

  public Optional<PublicacaoEntity> findById(Long id) {
    return publicacaoDao.findById(id);
  }

  public void delete(PublicacaoEntity publicacao) {
    publicacaoDao.delete(publicacao);
  }

}
