package cufa.conecta.com.br.resources.user;

import cufa.conecta.com.br.application.exception.EmpresaNotFoundException;
import cufa.conecta.com.br.application.exception.UsuarioNotFoundException;
import cufa.conecta.com.br.model.ExperienciaData;
import cufa.conecta.com.br.resources.user.dao.ExperienciasDao;
import cufa.conecta.com.br.resources.user.entity.ExperienciasEntity;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ExperienciasRepository {
  private final ExperienciasDao experienciasDao;

  public ExperienciasRepository(ExperienciasDao experienciasDao) {
    this.experienciasDao = experienciasDao;
  }

  public void criarExperiencia(ExperienciaData experienciaDto) {
    ExperienciasEntity experienciasEntity = toEntity(experienciaDto);

    experienciasDao.save(experienciasEntity);
  }

  private ExperienciasEntity toEntity(ExperienciaData experienciaData) {
    ExperienciasEntity entity = new ExperienciasEntity();
    entity.setCargo(experienciaData.getCargo());
    entity.setEmpresa(experienciaData.getEmpresa());
    entity.setDt_inicio(experienciaData.getDtInicio());
    entity.setDt_fim(experienciaData.getDtFim());
    entity.setFk_usuario(experienciaData.getFkUsuario());

    return entity;
  }

  public List<ExperienciasEntity> listarPorUsuario(Long fkUsuario) {
    return experienciasDao.findByFkUsuario(fkUsuario);
  }

  public void atualizar(ExperienciaData experiencia) {
    ExperienciasEntity experienciaExistente =
        experienciasDao
            .findById(experiencia.getId())
            .orElseThrow(() -> new UsuarioNotFoundException("Experiencia não encontrada"));

    experienciaExistente.setCargo(experiencia.getCargo());
    experienciaExistente.setEmpresa(experiencia.getEmpresa());
    experienciaExistente.setDt_inicio(experiencia.getDtInicio());
    experienciaExistente.setDt_fim(experiencia.getDtFim());

    experienciasDao.save(experienciaExistente);
  }

  public void deletar(Long id) {
    ExperienciasEntity experiencia =
        experienciasDao
            .findById(id)
            .orElseThrow(() -> new EmpresaNotFoundException("Experiencia não encontrada"));

    experienciasDao.delete(experiencia);
  }
}
