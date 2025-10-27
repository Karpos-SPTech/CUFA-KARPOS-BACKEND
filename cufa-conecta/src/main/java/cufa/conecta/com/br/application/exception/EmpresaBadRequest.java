package cufa.conecta.com.br.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmpresaBadRequest extends RuntimeException {
  public EmpresaBadRequest(String message) {
    super(message);
  }
}
