package cufa.conecta.com.br.application.controller.usuarios;

import cufa.conecta.com.br.application.dto.response.usuario.CandidaturaFilaDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notifications-candidaturas")
public class NotificationsCandidaturasController {

    private final RabbitTemplate rabbitTemplate;

    public NotificationsCandidaturasController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public List<CandidaturaFilaDto> getNotifications() {
        List<CandidaturaFilaDto> notifications = new ArrayList<>();
        Object message;

        while (true) {
            message = rabbitTemplate.receiveAndConvert("fila-candidaturas");
            if (message == null) break;

            if (message instanceof CandidaturaFilaDto) {
                notifications.add((CandidaturaFilaDto) message);
            }
        }

        return notifications;
    }
}