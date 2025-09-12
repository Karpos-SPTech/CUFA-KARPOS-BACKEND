package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.dto.response.ApiExceptionDto;
import cufa.conecta.com.br.application.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({Exception.class, DatabaseInternalServerError.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiExceptionDto handleInternalServerErrorException(Exception ex) {
    return new ApiExceptionDto(
        HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getClass().getTypeName());
  }

  @ExceptionHandler({BadRequestException.class, UsuarioBadRequest.class, EmpresaBadRequest.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiExceptionDto handleBadRequestException(Exception ex) {
    return new ApiExceptionDto(
        HttpStatus.BAD_REQUEST,
        ex.getMessage() != null ? ex.getMessage() : "Bad Request",
        ex.getClass().getTypeName());
  }

  @ExceptionHandler({
    NotFoundException.class,
    UsuarioNotFoundException.class,
    EmpresaNotFoundException.class
  })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ApiExceptionDto handleNotFoundException(Exception ex) {
    return new ApiExceptionDto(
        HttpStatus.NOT_FOUND,
        ex.getMessage() != null ? ex.getMessage() : "Not Found",
        ex.getClass().getTypeName());
  }
}
