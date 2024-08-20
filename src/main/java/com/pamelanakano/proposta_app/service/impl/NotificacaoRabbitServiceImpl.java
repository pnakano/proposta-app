package com.pamelanakano.proposta_app.service.impl;

import com.pamelanakano.proposta_app.model.Proposta;
import com.pamelanakano.proposta_app.service.interfaces.NotificacaoRabbitService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoRabbitServiceImpl implements NotificacaoRabbitService {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoRabbitServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void notificar(Proposta proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
