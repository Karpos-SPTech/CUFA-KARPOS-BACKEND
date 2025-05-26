package cufa.conecta.com.br.resources.user;

import cufa.conecta.com.br.application.exception.UsuarioNotFoundException;
import cufa.conecta.com.br.model.ExperienciaData;
import cufa.conecta.com.br.resources.user.dao.ExperienciasDao;
import cufa.conecta.com.br.resources.user.entity.ExperienciasEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExperienciasRepository {
    private final ExperienciasDao experienciasDao;

    public ExperienciasRepository(ExperienciasDao experienciasDao) {
        this.experienciasDao = experienciasDao;
    }

    public void criarExperiencia(ExperienciaData experienciaDto){
        ExperienciasEntity experienciasEntity = toEntity(experienciaDto);

        experienciasDao.save(experienciasEntity);
    }

    private ExperienciasEntity toEntity(ExperienciaData experienciaData) {
        ExperienciasEntity entity = new ExperienciasEntity();
        entity.setCargo(experienciaData.getCargo());
        entity.setEmpresa(experienciaData.getEmpresa());
        entity.setCidade(experienciaData.getCidade());
        entity.setEstado(experienciaData.getEstado());
        entity.setDt_inicio(experienciaData.getDtInicio());
        entity.setDt_fim(experienciaData.getDtFim());
        entity.setFk_usuario(experienciaData.getFkUsuario());

        return entity;
    }

    public List<ExperienciasEntity> listarTodas(){
        return experienciasDao.findAll();
    }

    public void atualizar(ExperienciaData experiencia){
        ExperienciasEntity experienciaExistente = experienciasDao.findById(experiencia.getId())
                .orElseThrow(() -> new UsuarioNotFoundException("Experiencia não encontrada"));

        experienciaExistente.setCargo(experiencia.getCargo());
        experienciaExistente.setEmpresa(experiencia.getEmpresa());
        experienciaExistente.setCidade(experiencia.getCidade());
        experienciaExistente.setEstado(experiencia.getEstado());
        experienciaExistente.setDt_inicio(experiencia.getDtInicio());
        experienciaExistente.setDt_fim(experiencia.getDtFim());

        experienciasDao.save(experienciaExistente);
    }
}
