package cufa.conecta.com.br.domain.service;

import cufa.conecta.com.br.model.EmpresaData;
import cufa.conecta.com.br.resources.empresa.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    EmpresaRepository repository;

    public EmpresaService(EmpresaRepository repository) { this.repository = repository;}

    public void cadastrarEmpresa(EmpresaData empresaDto) {
        repository.cadastrarEmpresa(empresaDto);
    }

    public List<EmpresaData> buscarEmpresas(Integer pagina, Integer tamanho) {
        return repository.buscarEmpresas(pagina, tamanho);
    }

    public void atualizarEmpresa(EmpresaData empresa) {
        repository.atualizar(empresa);
    }

    public void deletar(Integer id) {
        repository.deletar(id);
    }
}