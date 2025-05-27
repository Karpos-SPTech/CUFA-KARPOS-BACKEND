package cufa.conecta.com.br.domain.service;

import cufa.conecta.com.br.application.dto.request.usuario.ExperienciaRequestDto;
import cufa.conecta.com.br.application.dto.response.usuario.ExperienciaResponseDto;
import cufa.conecta.com.br.model.ExperienciaData;
import cufa.conecta.com.br.resources.user.ExperienciasRepository;
import cufa.conecta.com.br.resources.user.dao.ExperienciasDao;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import cufa.conecta.com.br.resources.user.entity.ExperienciasEntity;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ExperienciaService {
    private final ExperienciasDao experienciasDao;
    private final ExperienciasRepository repository;
    private final UsuarioDao usuarioDao;

    public ExperienciaService(ExperienciasDao experienciasDao, ExperienciasRepository repository, UsuarioDao usuarioDao) {
        this.experienciasDao = experienciasDao;
        this.repository = repository;
        this.usuarioDao = usuarioDao;
    }

    public void criarExperiencia(ExperienciaRequestDto experienciaDto, String email) {
        UsuarioEntity usuario = usuarioDao.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Usuario não encontrado com email: " + email));

        ExperienciaData experienciaData = new ExperienciaData(
                experienciaDto.getCargo(),
                experienciaDto.getEmpresa(),
                experienciaDto.getCidade(),
                experienciaDto.getEstado(),
                experienciaDto.getDtInicio(),
                experienciaDto.getDtFim(),
                usuario.getId()
        );

        repository.criarExperiencia(experienciaData);
    }

    public List<ExperienciaResponseDto> listarPorUsuario(Long fkUsuario) {
        List<ExperienciasEntity> experienciasEncontradas = repository.listarPorUsuario(fkUsuario);

        return experienciasEncontradas.stream()
                .map(experiencia -> new ExperienciaResponseDto(
                        experiencia.getCargo(),
                        experiencia.getEmpresa(),
                        experiencia.getCidade(),
                        experiencia.getEstado(),
                        experiencia.getDt_inicio(),
                        experiencia.getDt_fim())
                )
                .collect(Collectors.toList());
    }

    public void atualizarExperiencia(ExperienciaData experiencia){
        repository.atualizar(experiencia);
    }
}

