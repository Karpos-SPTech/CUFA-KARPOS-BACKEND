package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.documentation.EmpresaControllerDoc;
import cufa.conecta.com.br.application.dto.request.EmpresaRequestDto;
import cufa.conecta.com.br.application.dto.response.EmpresaResponseDto;
import cufa.conecta.com.br.domain.service.EmpresaService;
import cufa.conecta.com.br.model.EmpresaData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController implements EmpresaControllerDoc {
   private final EmpresaService service;

   public EmpresaController(EmpresaService service) {
      this.service = service;
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public void cadastrarEmpresa(@RequestBody EmpresaRequestDto empresaDto) {
      EmpresaData data = empresaDto.toModel();

      service.cadastrarEmpresa(data);
   }

   @GetMapping
   @ResponseStatus(HttpStatus.OK)
   public List<EmpresaResponseDto> buscarEmpresa(
           @RequestParam(defaultValue = "1") Integer pagina,
           @RequestParam(defaultValue = "15") Integer tamanho
   ) {
      List<EmpresaData> listaDeEmpresas = service.buscarEmpresas(pagina -1, tamanho);
      List<EmpresaResponseDto> listaDeResponseDaEmpresa = new ArrayList<>();

      for (EmpresaData empresa: listaDeEmpresas) {
         listaDeResponseDaEmpresa.add(EmpresaResponseDto.of(empresa));
      }

      return listaDeResponseDaEmpresa;
   }

   @PutMapping("/{id}")
   public void atualizarEmpresa(@PathVariable Integer id, @RequestBody EmpresaRequestDto empresaDto) {
      EmpresaData empresaAtualizada = empresaDto.toModel();
      empresaAtualizada.setId(id);

      service.atualizarEmpresa(empresaAtualizada);
   }

   @DeleteMapping("/{id}")
   public void deletarEmpresa(@PathVariable Integer id) { service.deletar(id); }
}
