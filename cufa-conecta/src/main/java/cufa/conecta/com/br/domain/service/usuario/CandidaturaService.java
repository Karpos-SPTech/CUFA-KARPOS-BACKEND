package cufa.conecta.com.br.domain.service.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import cufa.conecta.com.br.application.dto.request.usuario.CandidaturaRequestDto;
import cufa.conecta.com.br.application.dto.response.empresa.PublicacaoResponseDto;
import cufa.conecta.com.br.application.dto.response.usuario.CandidatoDto;
import cufa.conecta.com.br.application.dto.response.usuario.CandidaturaFilaDto;
import cufa.conecta.com.br.application.dto.response.usuario.CandidaturaResponseDto;
import cufa.conecta.com.br.application.dto.response.usuario.ExperienciaResponseDto;
import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.dao.PublicacaoDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;
import cufa.conecta.com.br.resources.user.CandidaturaRepository;
import cufa.conecta.com.br.resources.user.dao.CandidaturaDao;
import cufa.conecta.com.br.resources.user.dao.ExperienciasDao;
import cufa.conecta.com.br.resources.user.dao.UsuarioDao;
import cufa.conecta.com.br.resources.user.entity.CandidaturaEntity;
import cufa.conecta.com.br.resources.user.entity.ExperienciasEntity;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cufa.conecta.com.br.config.RabbitConfig.QUEUE_CANDIDATURAS;
import static cufa.conecta.com.br.config.RabbitConfig.QUEUE_NAME;

@Service
public class CandidaturaService {
  private final CandidaturaRepository repository;
  private final UsuarioDao userDao;
  private final PublicacaoDao publiDao;
  private final ExperienciasDao expDao;
  private final EmpresaDao empresaDao;
  private final CandidaturaDao dao;
  private final RabbitTemplate rabbitTemplate;

    public CandidaturaService(CandidaturaRepository repository, UsuarioDao userDao, PublicacaoDao publiDao, ExperienciasDao expDao, EmpresaDao empresaDao, CandidaturaDao dao, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.userDao = userDao;
        this.publiDao = publiDao;
        this.expDao = expDao;
        this.empresaDao = empresaDao;
        this.dao = dao;
        this.rabbitTemplate = rabbitTemplate;
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

        CandidaturaFilaDto filaDto = new CandidaturaFilaDto(usuario.getNome(), publicacao.getTitulo());
        rabbitTemplate.convertAndSend("fila-candidaturas", filaDto);
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
                    usuario.getEscolaridade(),
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


    }

    public boolean verificarCandidaturaExistente(Long userId, Long vagaId) {

        PublicacaoEntity vaga = publiDao.findById(vagaId)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        UsuarioEntity usuario = userDao.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return dao.existsByUsuarioAndPublicacao(usuario, vaga);
    }

    public List<PublicacaoResponseDto> listarPublicacoesCandidatadasPorUsuario(Long userId) {
        UsuarioEntity usuario = userDao.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + userId));

        List<CandidaturaEntity> candidaturas = dao.findByUsuario(usuario);

        Set<PublicacaoEntity> publicacoesUnicas = candidaturas.stream()
                .map(CandidaturaEntity::getPublicacao)
                .collect(Collectors.toSet());


        return publicacoesUnicas.stream()
                .map(this::mapPublicacaoEntityToResponseDto)
                .collect(Collectors.toList());
    }

    private PublicacaoResponseDto mapPublicacaoEntityToResponseDto(PublicacaoEntity publicacaoEntity) {
        return new PublicacaoResponseDto(publicacaoEntity);
    }
}
