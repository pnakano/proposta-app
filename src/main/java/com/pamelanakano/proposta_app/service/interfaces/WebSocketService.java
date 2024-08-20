package com.pamelanakano.proposta_app.service.interfaces;

import com.pamelanakano.proposta_app.http.dto.PropostaResponseDto;

public interface WebSocketService {

    void notificar(PropostaResponseDto proposta);

}
