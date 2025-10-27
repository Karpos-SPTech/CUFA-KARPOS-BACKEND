package cufa.conecta.com.br.application.controller.empresas;

import cufa.conecta.com.br.application.dto.response.empresa.PublicacaoResponseDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final RabbitTemplate rabbitTemplate;

    public NotificationController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public List<PublicacaoResponseDto> getNotifications() {
        List<PublicacaoResponseDto> notifications = new ArrayList<>();
        Object message;

        while (true) {
            message = rabbitTemplate.receiveAndConvert("fila-notificacoes");
            if (message == null) break;
            notifications.add((PublicacaoResponseDto) message);
        }

        return notifications;
    }
}
