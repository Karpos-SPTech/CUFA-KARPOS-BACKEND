package cufa.conecta.com.br.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioBadRequest extends RuntimeException {
    public UsuarioBadRequest(String message) {
        super(message);
    }
}