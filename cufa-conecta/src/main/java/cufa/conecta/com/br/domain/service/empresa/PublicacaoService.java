package cufa.conecta.com.br.domain.service.empresa;

import com.fasterxml.jackson.databind.ObjectMapper;

import cufa.conecta.com.br.application.dto.request.empresa.PublicacaoRequestDto;
import cufa.conecta.com.br.application.dto.response.empresa.PublicacaoResponseDto;
import cufa.conecta.com.br.resources.empresa.PublicacaoRepository;
import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static cufa.conecta.com.br.config.RabbitConfig.QUEUE_NAME;

@Service
public class PublicacaoService {

    private final PublicacaoRepository repository;
    private final EmpresaDao empresaDao;
    private final RabbitTemplate rabbitTemplate;

    public PublicacaoService(PublicacaoRepository repository, EmpresaDao empresaDao, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.empresaDao = empresaDao;
        this.rabbitTemplate = rabbitTemplate;
    }

    public PublicacaoResponseDto criarPublicacao(PublicacaoRequestDto dto) {
        String emailEmpresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();

        EmpresaEntity empresa =
                empresaDao
                        .findByEmail(emailEmpresaLogada)
                        .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        PublicacaoEntity publicacao = new PublicacaoEntity();
        publicacao.setEmpresa(empresa);
        publicacao.setTitulo(dto.titulo);
        publicacao.setDescricao(dto.descricao);
        publicacao.setTipoContrato(dto.tipoContrato);
        publicacao.setDtExpiracao(dto.dtExpiracao);
        publicacao.setDtPublicacao(LocalDateTime.now());


        PublicacaoEntity salvo = repository.save(publicacao);
        rabbitTemplate.convertAndSend(QUEUE_NAME, publicacao);
        return new PublicacaoResponseDto(salvo);

    }


    public List<PublicacaoResponseDto> listarTodasPublicacoes() {
        List<PublicacaoEntity> publicacoes = repository.buscarTodas();
        return publicacoes.stream().map(PublicacaoResponseDto::new).toList();
    }

    public List<PublicacaoResponseDto> listarPublicacoesDaEmpresa() {
        String emailEmpresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();

        EmpresaEntity empresa =
                empresaDao
                        .findByEmail(emailEmpresaLogada)
                        .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        List<PublicacaoEntity> publicacoes = repository.buscarPorEmpresa(empresa);

        return publicacoes.stream().map(PublicacaoResponseDto::new).toList();
    }

    public PublicacaoResponseDto editarPublicacao(Long id, PublicacaoRequestDto dto) {
//    String emailEmpresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();
//    EmpresaEntity empresa =
//        empresaDao
//            .findByEmail(emailEmpresaLogada)
//            .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        PublicacaoEntity publicacao =
                repository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("Publicação não encontrada"));

        publicacao.setDescricao(dto.descricao);
        publicacao.setTipoContrato(dto.tipoContrato);
        publicacao.setDtExpiracao(dto.dtExpiracao);

        PublicacaoEntity atualizada = repository.save(publicacao);
        return new PublicacaoResponseDto(atualizada);
    }

    public void deletarPublicacao(Long id) {
//    String emailEmpresaLogada = SecurityContextHolder.getContext().getAuthentication().getName();
//    EmpresaEntity empresa =
//        empresaDao
//            .findByEmail(emailEmpresaLogada)
//            .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        PublicacaoEntity publicacao =
                repository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("Publicação não encontrada"));

        repository.delete(publicacao);
    }
}
