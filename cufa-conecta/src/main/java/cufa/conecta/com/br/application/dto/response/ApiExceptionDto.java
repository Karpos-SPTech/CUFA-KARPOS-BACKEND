package cufa.conecta.com.br.application.dto.response;

import org.springframework.http.HttpStatus;

public class ApiExceptionDto {
  HttpStatus status;
  String message;
  String type;

  public ApiExceptionDto(HttpStatus status, String message, String type) {
    this.status = status;
    this.message = message;
    this.type = type;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public String getType() {
    return type;
  }
}
