package cufa.conecta.com.br.resources.user;

import cufa.conecta.com.br.model.PerfilUsuarioData;
import cufa.conecta.com.br.resources.user.dao.PerfilUsuarioDao;
import cufa.conecta.com.br.resources.user.entity.PerfilUsuarioEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PerfilUsuarioRepository {
    private final PerfilUsuarioDao perfilUsuarioDao;

    public PerfilUsuarioRepository(PerfilUsuarioDao perfilUsuarioDao) {
        this.perfilUsuarioDao = perfilUsuarioDao;
    }

    public void criarPerfilUsuario(PerfilUsuarioData userDto) {
        PerfilUsuarioEntity perfilUsuarioEntity = toEntity(userDto);

        perfilUsuarioDao.save(perfilUsuarioEntity);
    }

    private PerfilUsuarioEntity toEntity(PerfilUsuarioData perfilUsuarioData) {
        PerfilUsuarioEntity entity = new PerfilUsuarioEntity();
        entity.setCpf(perfilUsuarioData.getCpf());
        entity.setTelefone(perfilUsuarioData.getTelefone());
        entity.setEscolaridade(perfilUsuarioData.getEscolaridade());
        entity.setDtNascimento(perfilUsuarioData.getDtNascimento());
        entity.setEstadoCivil(perfilUsuarioData.getEstadoCivil());
        entity.setEstado(perfilUsuarioData.getEstado());
        entity.setCidade(perfilUsuarioData.getCidade());
        entity.setBiografia(perfilUsuarioData.getBiografia());
        entity.setFkUsuario(perfilUsuarioData.getFkUsuario());

        return entity;
    }
}
