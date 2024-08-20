package com.pamelanakano.proposta_app.service.interfaces;

import com.pamelanakano.proposta_app.model.Proposta;

public interface NotificacaoRabbitService {

    void notificar(Proposta proposta, String exchange);

}
