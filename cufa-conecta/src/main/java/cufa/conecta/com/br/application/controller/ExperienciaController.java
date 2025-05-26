package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.dto.request.usuario.ExperienciaRequestDto;
import cufa.conecta.com.br.application.dto.response.usuario.ExperienciaResponseDto;
import cufa.conecta.com.br.domain.service.ExperienciaService;
import cufa.conecta.com.br.model.ExperienciaData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiencias")
public class ExperienciaController {
    private final ExperienciaService experienciaService;

    public ExperienciaController(ExperienciaService experienciaService) {
        this.experienciaService = experienciaService;
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarExperiencia(@RequestBody @Valid ExperienciaRequestDto experienciaDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        experienciaService.criarExperiencia(experienciaDto, email);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ExperienciaResponseDto>> listarExperiencias() {
        List<ExperienciaResponseDto> experienciasEncontradas = experienciaService.listarTodas();

        if (experienciasEncontradas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(experienciasEncontradas);
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarExperiencia(@PathVariable Long id, @RequestBody ExperienciaRequestDto experienciaDto){
        ExperienciaData experienciaParaAtualizar = experienciaDto.toModel();
        experienciaParaAtualizar.setId(id);

        experienciaService.atualizarExperiencia(experienciaParaAtualizar);
    }
}


