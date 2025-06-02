package cufa.conecta.com.br.application.controller;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

  private final Path uploadDir =
      Paths.get("uploads/curriculos"); // ou configure via `application.properties`

  @PostConstruct
  public void init() throws IOException {
    Files.createDirectories(uploadDir);
  }

  @PostMapping("/upload")
  public ResponseEntity<String> uploadCurriculo(@RequestParam("file") MultipartFile file) {
    try {
      if (file.isEmpty()) return ResponseEntity.badRequest().body("Arquivo vazio.");

      String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
      Path targetPath = uploadDir.resolve(filename);
      Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

      // Aqui você pode associar `filename` ao usuário logado no banco de dados

      return ResponseEntity.ok(filename);
    } catch (IOException e) {
      return ResponseEntity.status(500).body("Erro ao salvar currículo.");
    }
  }

  @GetMapping("/download/{filename:.+}")
  public ResponseEntity<Resource> downloadCurriculo(@PathVariable String filename) {
    try {
      Path filePath = uploadDir.resolve(filename).normalize();
      Resource resource = new UrlResource(filePath.toUri());

      if (!resource.exists()) {
        return ResponseEntity.notFound().build();
      }

      return ResponseEntity.ok()
          .contentType(MediaType.APPLICATION_OCTET_STREAM)
          .header(
              HttpHeaders.CONTENT_DISPOSITION,
              "attachment; filename=\"" + resource.getFilename() + "\"")
          .body(resource);

    } catch (MalformedURLException e) {
      return ResponseEntity.status(500).build();
    }
  }
}
