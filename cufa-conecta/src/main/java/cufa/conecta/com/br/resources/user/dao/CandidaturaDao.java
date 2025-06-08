package cufa.conecta.com.br.resources.user.dao;

import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;
import cufa.conecta.com.br.resources.user.entity.CandidaturaEntity;
import cufa.conecta.com.br.resources.user.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidaturaDao extends JpaRepository<CandidaturaEntity, Long> {
    boolean existsByUsuarioAndPublicacao(UsuarioEntity usuario, PublicacaoEntity vaga);
    List<CandidaturaEntity> findByUsuario(UsuarioEntity usuario);
}
