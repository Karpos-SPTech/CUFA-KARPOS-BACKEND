package cufa.conecta.com.br.application.exception;

public class DatabaseInternalServerError extends RuntimeException {

    public DatabaseInternalServerError(String message) {
        super(message);
    }
}