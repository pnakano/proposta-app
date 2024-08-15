package com.pamelanakano.proposta_app.service;

import com.pamelanakano.proposta_app.http.dto.PropostaResponseDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    private final RabbitTemplate rabbitTemplate;

    public NotificacaoServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void notify(PropostaResponseDto proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }

}
