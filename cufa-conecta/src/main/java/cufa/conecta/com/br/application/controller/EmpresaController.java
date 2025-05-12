package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.documentation.EmpresaControllerDoc;
import cufa.conecta.com.br.application.dto.request.empresa.EmpresaRequestDto;
import cufa.conecta.com.br.application.dto.request.LoginDto;
import cufa.conecta.com.br.application.dto.response.empresa.EmpresaResponseDto;
import cufa.conecta.com.br.application.dto.response.empresa.EmpresaTokenDto;
import cufa.conecta.com.br.domain.service.EmpresaService;
import cufa.conecta.com.br.model.EmpresaData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController implements EmpresaControllerDoc {
   private final EmpresaService service;

   public EmpresaController(EmpresaService service) {
      this.service = service;
   }

   @PostMapping
   @SecurityRequirement(name = "Bearer")
   @ResponseStatus(HttpStatus.CREATED)
   public void cadastrarEmpresa(@RequestBody @Valid EmpresaRequestDto empresaDto) {
      service.cadastrarEmpresa(empresaDto);
   }

   @PostMapping("/login")
   @ResponseStatus(HttpStatus.OK)
   public EmpresaTokenDto login(@RequestBody LoginDto empresaLoginDto) {
      return service.login(empresaLoginDto);
   }

   @GetMapping
   @SecurityRequirement(name = "Bearer")
   public ResponseEntity<List<EmpresaResponseDto>> listarEmpresas() {
      List<EmpresaResponseDto> empresasEncontradas = service.listarTodos();

      if (empresasEncontradas.isEmpty()){
         return ResponseEntity.status(204).build();
      }
      return ResponseEntity.status(200).body(empresasEncontradas);
   }

   @PutMapping("/{id}")
   public void atualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaRequestDto empresaDto) {
      EmpresaData empresaAtualizada = empresaDto.toModel();
      empresaAtualizada.setId(id);

      service.atualizarEmpresa(empresaAtualizada);
   }

   @DeleteMapping("/{id}")
   public void deletarEmpresa(@PathVariable Long id) { service.deletar(id); }
}
