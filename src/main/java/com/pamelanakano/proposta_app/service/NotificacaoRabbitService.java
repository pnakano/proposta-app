package com.pamelanakano.proposta_app.service;

import com.pamelanakano.proposta_app.model.Proposta;

public interface NotificacaoRabbitService {

    void notify(Proposta proposta, String exchange);

}
