package cufa.conecta.com.br.domain.service.observer;

import cufa.conecta.com.br.model.UsuarioData;
import org.springframework.stereotype.Component;

@Component
public class LogObserver implements CadastroObserver{
    @Override
    public void notificar(UsuarioData usuario) {
        System.out.println("Log registrado: Usuario com email: " + usuario.getEmail() + " cadastrado com sucesso");
    }
}
