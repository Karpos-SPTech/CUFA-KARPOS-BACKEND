package cufa.conecta.com.br.resources.empresa;

import cufa.conecta.com.br.model.EmpresaData;
import cufa.conecta.com.br.resources.empresa.dao.EmpresaDao;
import cufa.conecta.com.br.resources.empresa.entity.EmpresaEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class EmpresaRepository {
    private final EmpresaDao empresaDao;

    public EmpresaRepository(EmpresaDao empresaDao) { this.empresaDao = empresaDao; }

    public void cadastrarEmpresa(EmpresaData empresaDto) {
        EmpresaEntity empresaEntity = toEntity(empresaDto);

        if (empresaDao.findByEmail(empresaEntity.getEmail()) != null) {
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }
        empresaDao.save(empresaEntity);
    }

    public List<EmpresaData> buscarEmpresas(Integer pagina, Integer tamanho) {
        List<EmpresaEntity> listaDeEntityDaEmpresa = empresaDao.findAll(
                PageRequest.of(pagina, tamanho)
        ).toList();

        List<EmpresaData> listasDeEmpresas = new ArrayList<>();

        for (EmpresaEntity empresa : listaDeEntityDaEmpresa) {
            EmpresaData empresaData = new EmpresaData(empresa.getNome(), empresa.getEmail(),empresa.getSenha(), empresa.getCep(),
                    empresa.getNumero(), empresa.getEndereco(), empresa.getCnpj(), empresa.getArea());
            listasDeEmpresas.add(empresaData);
        }
        return listasDeEmpresas;
    }

    public void atualizar(EmpresaData empresa) {
        EmpresaEntity usuarioExistente = empresaDao.findById(empresa.getId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        usuarioExistente.setNome(empresa.getNome());
        usuarioExistente.setEmail(empresa.getEmail());
        usuarioExistente.setSenha(empresa.getSenha());

        empresaDao.save(usuarioExistente);
    }

    public void deletar(Integer id) {
        EmpresaEntity empresa = empresaDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        empresaDao.delete(empresa);
    }

//     ------------------ Método privado para conversão de dados --------------

    private EmpresaEntity toEntity(EmpresaData empresaData) {
        EmpresaEntity entity = new EmpresaEntity();
        entity.setNome(empresaData.getNome());
        entity.setEmail(empresaData.getEmail());
        entity.setSenha(empresaData.getSenha());
        entity.setCep(empresaData.getCep());
        entity.setNumero(empresaData.getNumero());
        entity.setEndereco(empresaData.getEndereco());
        entity.setCnpj(empresaData.getCnpj());
        entity.setArea(empresaData.getArea());

        return entity;
    }
}
