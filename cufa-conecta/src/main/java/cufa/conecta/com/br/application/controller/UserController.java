package cufa.conecta.com.br.application.controller;

import cufa.conecta.com.br.application.UserResponseDto;
import cufa.conecta.com.br.application.request.UserRequestDto;
import cufa.conecta.com.br.domain.service.UserService;
import cufa.conecta.com.br.model.UserData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    UserService service;

    public UserController(UserService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario(@RequestBody UserRequestDto userDto) {
        UserData data = userDto.toModel();

        service.cadastrarUsuario(data);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> buscarUsuario(
            @RequestParam(defaultValue = "1") Integer pagina,
            @RequestParam(defaultValue = "15") Integer tamanho
    ) {
        List<UserData> listaDeUsuarios = service.buscarUsuarios(pagina -1, tamanho);
        List<UserResponseDto> listaDeResponseDoUsuario = new ArrayList<>();

        for (UserData usuario: listaDeUsuarios) {
            listaDeResponseDoUsuario.add(UserResponseDto.of(usuario));
        }

        return listaDeResponseDoUsuario;
    }

    @PutMapping("/{id}")
    public void atualizarUsuario(@PathVariable Integer id, @RequestBody UserRequestDto usuarioDto) {
        UserData usuarioParaAtualizar = usuarioDto.toModel();
        usuarioParaAtualizar.setId(id);

        service.atualizarUsuario(usuarioParaAtualizar);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id) { service.deletar(id); }
}
