package com.pamelanakano.proposta_app.service.interfaces;

import com.pamelanakano.proposta_app.model.Proposta;
import org.springframework.amqp.core.MessagePostProcessor;

public interface NotificacaoRabbitService {

    void notificar(Proposta proposta, String exchange);

    void notificar(Proposta proposta, String exchange, MessagePostProcessor messagePostProcessor);

}
