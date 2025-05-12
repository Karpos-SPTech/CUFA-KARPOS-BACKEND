package cufa.conecta.com.br.application.documentation;

import cufa.conecta.com.br.application.dto.response.ApiExceptionDto;
import cufa.conecta.com.br.application.dto.request.UsuarioRequestDto;
import cufa.conecta.com.br.application.dto.response.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Projeto usuários",
                description = "Exemplo de implementação de JWT com Spring Security"
        )
)
@SecurityScheme(
        name = "Bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"
)
@Validated
@Tag(
        name = "Usuario Controller",
        description = "Responsável por executar o CRUD de Usuarios"
)
public interface UsuarioControllerDoc {
    @Operation( summary = "Requisição responsável pela recuperação dos usuários cadastrados" )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Requisição retornada com sucesso"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Algum erro mapeado não foi encontrado",
            content = {
                @Content(
                    schema = @Schema(implementation = ApiExceptionDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Erro na busca dos usuários",
                            value = """
                                {
                                "status": "INTERNAL_SERVER_ERROR",
                                "message": "Banco de dados fora de serviço"
                                }
                                """
                        )
                    }
                )
            }
        )
    })
    ResponseEntity<List<UsuarioResponseDto>> listarUsuarios();

    @Operation( summary = "Cria um novo usuário dentro do serviço" )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "O usuário foi criado com sucesso!",
            content = {
                @Content(
                    schema = @Schema(implementation = UsuarioResponseDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Obteve êxito na criação do usuário",
                            value = """
                                {
                                    "id": "1",
                                    "name": "Usuario Novo",
                                    "email": "usuario.novo@gmail.com",
                                    "senha": "12345678"
                                }
                                """
                        )
                    }
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro na requisição do usuário",
            content = {
                @Content(
                    schema = @Schema(implementation = ApiExceptionDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Os dados inseridos do usuário são inválidos",
                            value = """
                                {
                                    "status": "BAD_REQUEST",
                                    "message": "O usuário inserido é inválido"
                                }
                                """
                        )
                    }
                )
            }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno no servidor",
            content = {
                @Content(
                    schema = @Schema(implementation = ApiExceptionDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Erro na busca do usuário",
                            value = """
                                {
                                "status": "INTERNAL_SERVER_ERROR",
                                "message": "Banco de dados fora de serviço"
                                }
                                """
                        )
                    }
                )
            }
        )
    })
    void cadastrarUsuario(@Valid @Parameter UsuarioRequestDto usuarioDto);

    @Operation( summary = "Atualiza os dados do usuário dentro do serviço" )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Os dados do usuário foram alterados com sucesso!",
            content = {
                @Content(
                    schema = @Schema(implementation = UsuarioResponseDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Obteve êxito na alteração dos dados do usuário",
                            value = """
                                {
                                "id": "1",
                                "name": "Novo usuário",
                                "email": "novo.usuario@gmail.com",
                                "senha": "12345677"
                                }
                                """
                        )
                    }
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "O usuário inserido não foi encontrado",
            content = {
                @Content(
                    schema = @Schema(implementation = ApiExceptionDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "O id da empresa não foi encontrado",
                            value = """
                                {
                                "status": "NOT_FOUND",
                                "message": "O usuário inserido não foi encontrado"
                                }
                                """
                        )
                    }
                )
            }
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno no servidor",
            content = {
                @Content(
                    schema = @Schema(implementation = ApiExceptionDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Erro na busca do usuário",
                            value = """
                                {
                                "status": "INTERNAL_SERVER_ERROR",
                                "message": "Banco de dados fora de serviço"
                                }
                                """
                        )
                    }
                )
            }
        )
    })
    void atualizarUsuario(@Valid Long id, @Valid UsuarioRequestDto usuarioDto);

    @Operation( summary = "Realiza a deleção de um usuário" )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "O usuário foi deletado com sucesso!"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "O usuário inserido não foi encontrado",
            content = {
                @Content(
                    schema = @Schema(implementation = ApiExceptionDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Erro na busca do usuário",
                            value = """
                                {
                                "status": "NOT_FOUND",
                                "message": "O usuário inserido não foi encontrado"
                                }
                                """
                        )
                    }
                )
            }
        )
    })
    void deletarUsuario(@Valid Long id);
}
