package cufa.conecta.com.br.domain.service.usuario;

import cufa.conecta.com.br.application.dto.request.usuario.CandidaturaRequestDto;
import cufa.conecta.com.br.application.dto.response.usuario.CandidatoDto;
import cufa.conecta.com.br.application.dto.response.usuario.CandidaturaResponseDto;
import cufa.conecta.com.br.application.dto.response.usuario.ExperienciaResponseDto;
import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.dao.PublicacaoDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;
import cufa.conecta.com.br.resources.user.CandidaturaRepository;
import cufa.conecta.com.br.resources.user.dao.ExperienciasDao;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import cufa.conecta.com.br.resources.user.entity.CandidaturaEntity;
import cufa.conecta.com.br.resources.user.entity.ExperienciasEntity;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidaturaService {
  private final CandidaturaRepository repository;
  private final UsuarioDao userDao;
  private final PublicacaoDao publiDao;
  private final ExperienciasDao expDao;
  private final EmpresaDao empresaDao;

    public CandidaturaService(CandidaturaRepository repository, UsuarioDao userDao, PublicacaoDao publiDao, ExperienciasDao expDao, EmpresaDao empresaDao) {
        this.repository = repository;
        this.userDao = userDao;
        this.publiDao = publiDao;
        this.expDao = expDao;
        this.empresaDao = empresaDao;
    }


    public void criarCandidatura(CandidaturaRequestDto candidaturaDto) {

        UsuarioEntity usuario = userDao.findById(candidaturaDto.getFkUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        PublicacaoEntity publicacao = publiDao.findById(candidaturaDto.getFkPublicacao())
                .orElseThrow(() -> new RuntimeException("Publicação não encontrada"));

        EmpresaEntity empresa = empresaDao.findById(candidaturaDto.getFkEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        CandidaturaEntity candidatura = new CandidaturaEntity(
                usuario,
                publicacao,
                empresa,
                LocalDate.now()
        );

        repository.criarCandidatura(candidatura);
    }

    public CandidaturaResponseDto listarCandidatosPorVaga(Long vagaId) {

        PublicacaoEntity vaga = publiDao.findById(vagaId)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        if (vaga.getEmpresa() == null) {
            throw new RuntimeException("A vaga não está vinculada a nenhuma empresa");
        }

        List<UsuarioEntity> usuarios = userDao.findUsuariosByPublicacaoId(vagaId);

        List<CandidatoDto> candidatos = usuarios.stream().map(usuario -> {
            Integer idade = null;
                if (usuario.getDt_nascimento() != null) {
                    idade = Period.between(usuario.getDt_nascimento(), LocalDate.now()).getYears();
                }


            List<ExperienciasEntity> experienciasUsuario = expDao.findByFkUsuario(usuario.getId());

            List<ExperienciaResponseDto> experiencias = experienciasUsuario.stream()
                    .map(exp -> new ExperienciaResponseDto(
                            exp.getCargo(),
                            exp.getEmpresa(),
                            exp.getDt_inicio(),
                            exp.getDt_fim()
                    )).collect(Collectors.toList());

            return new CandidatoDto(
                    usuario.getNome(),
                    idade != null ? idade : 0,
                    usuario.getBiografia(),
                    usuario.getEmail(),
                    usuario.getTelefone(),
                    usuario.getCurriculoUrl(),
                    experiencias
            );
        }).collect(Collectors.toList());

        return new CandidaturaResponseDto(
                vaga.getTitulo(),
                vaga.getEmpresa().getNome(),
                vaga.getTipoContrato(),
                vaga.getDtPublicacao(),
                vaga.getDtExpiracao(),
                candidatos.size(),
                candidatos
        );
    }}
