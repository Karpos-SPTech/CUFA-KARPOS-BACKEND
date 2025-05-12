package cufa.conecta.com.br.application.documentation;

import cufa.conecta.com.br.application.dto.response.ApiExceptionDto;
import cufa.conecta.com.br.application.dto.request.EmpresaRequestDto;
import cufa.conecta.com.br.application.dto.response.EmpresaResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Tag(
    name = "Empresa Controller",
    description = "Responsável por executar o CRUD de Empresas"
)
public interface EmpresaControllerDoc {
    @Operation( summary = "Requisição responsável pela recuperação das empresas cadastradas" )

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
                            name = "Erro na busca das empresas",
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
    ResponseEntity<List<EmpresaResponseDto>> listarEmpresas();

    @Operation( summary = "Cria uma nova empresa dentro do serviço" )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "A empresa foi criada com sucesso!",
            content = {
                @Content(
                    schema = @Schema(implementation = EmpresaRequestDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Obteve êxito na criação da empresa",
                            value = """
                                {
                                "id": "1",
                                "name": "Cufa",
                                "email": "cufa.conecta@gmail.com",
                                "senha": "12345678",
                                "cep": "01240530",
                                "numero": "12",
                                "endereco": "Rua Haddock Lobo",
                                "cnpj": "12345678901234",
                                "area": "networking"
                                }
                                """
                        )
                    }
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro na requisição da empresa",
            content = {
                @Content(
                    schema = @Schema(implementation = ApiExceptionDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Os dados inseridos da empresa são inválidos",
                            value = """
                                {
                                "status": "BAD_REQUEST",
                                "message": "A empresa inserida é inválida"
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
                            name = "Erro na busca da empresa",
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
    void cadastrarEmpresa(@Valid @Parameter EmpresaRequestDto empresaDto);

    @Operation( summary = "Atualiza os dados da empresa dentro do serviço" )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Os dados da empresa foram alterados com sucesso!",
            content = {
                @Content(
                    schema = @Schema(implementation = EmpresaResponseDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Obteve êxito na criação da empresa",
                            value = """
                                {
                                "id": "1",
                                "name": "Cufa",
                                "email": "cufa.conecta@gmail.com",
                                "senha": "12345678",
                                "cep": "01240530",
                                "numero": "12",
                                "endereco": "Rua Haddock Lobo",
                                "cnpj": "12345678901234",
                                "area": "networking"
                                }
                                """
                        )
                    }
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "A empresa inserida não foi encontrada",
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
                                "message": "A empresa inserida não foi encontrada"
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
                            name = "Erro na busca da empresa",
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
    void atualizarEmpresa(@Valid Long id, @Valid EmpresaRequestDto empresaDto);

    @Operation( summary = "Realiza a deleção de uma empresa" )

    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "A empresa foi deletada com sucesso!"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "A empresa inserida não foi encontrada",
            content = {
                @Content(
                    schema = @Schema(implementation = ApiExceptionDto.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                        @ExampleObject(
                            name = "Erro na busca da empresa",
                            value = """
                                {
                                "status": "NOT_FOUND",
                                "message": "A empresa inserida não foi encontrada"
                                }
                                """
                        )
                    }
                )
            }
        )
    })
    void deletarEmpresa(@Valid Long id);
}
