package cufa.conecta.com.br.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmpresaNotFoundException extends RuntimeException {
    public EmpresaNotFoundException(String message) {
        super(message);
    }
}