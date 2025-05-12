package cufa.conecta.com.br.domain.service.observer;

import cufa.conecta.com.br.model.UsuarioData;

public interface CadastroObserver {
    void notificar(UsuarioData usuario);
}
