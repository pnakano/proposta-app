package com.pamelanakano.proposta_app.service;

import com.pamelanakano.proposta_app.http.dto.PropostaResponseDto;

public interface NotificacaoService {

    void notify(PropostaResponseDto proposta, String exchange);

}
