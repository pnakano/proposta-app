package com.pamelanakano.proposta_app.listener;

import com.pamelanakano.proposta_app.model.Proposta;
import com.pamelanakano.proposta_app.repository.PropostaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    private final PropostaRepository propostaRepository;

    public PropostaConcluidaListener(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @RabbitListener(queues = "${rabbitmq.propostaconcluida.proposta}")
    public void propostaConcluida(Proposta proposta) {
        propostaRepository.save(proposta);
    }

}
