package cufa.conecta.com.br.resources.empresa.dao;

import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import cufa.conecta.com.br.resources.empresa.entity.PublicacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacaoDao extends JpaRepository<PublicacaoEntity, Long> {
    List<PublicacaoEntity> findAllByEmpresa(EmpresaEntity empresa);
}
