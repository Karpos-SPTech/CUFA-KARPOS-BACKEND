package cufa.conecta.com.br.application.controller;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/curriculos")
public class CurriculoController {

  private final Path uploadDir = Paths.get("uploads/curriculos");

  @PostConstruct
  public void init() {
    try {
      Files.createDirectories(uploadDir);
    } catch (IOException e) {
      throw new RuntimeException("Não foi possível criar o diretório de uploads.", e);
    }
  }

  public String salvarArquivoCurriculo(MultipartFile file) throws IOException {
    if (file.isEmpty()) {
      throw new IllegalArgumentException("Arquivo vazio.");
    }

    if (!Files.exists(uploadDir)) {
      Files.createDirectories(uploadDir);
    }

    String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
    Path targetPath = uploadDir.resolve(filename);

    Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
    return filename;
  }

  @GetMapping("/download/{filename:.+}")
  public ResponseEntity<Resource> downloadCurriculo(@PathVariable String filename) {
    try {
      Path filePath = uploadDir.resolve(filename).normalize();
      Resource resource = new UrlResource(filePath.toUri());

      if (!resource.exists() || !resource.isReadable()) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok()
              .contentType(MediaType.APPLICATION_OCTET_STREAM) // Tipo binário genérico
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
              .body(resource);
    } catch (MalformedURLException e) {
      return ResponseEntity.status(500).body(null);
    }
  }

  public ResponseEntity<String> deletarArquivoFisico(String filename) {
    try {
      Path filePath = uploadDir.resolve(filename).normalize();

      if (Files.exists(filePath)) {
        Files.delete(filePath);
        return ResponseEntity.ok("Arquivo físico deletado com sucesso.");
      } else {
        return ResponseEntity.status(404).body("Arquivo físico não encontrado para exclusão.");
      }
    } catch (IOException e) {
      return ResponseEntity.status(500).body("Erro ao excluir currículo físico: " + e.getMessage());
    }
  }

}