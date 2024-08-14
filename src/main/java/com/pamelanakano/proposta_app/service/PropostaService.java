package com.pamelanakano.proposta_app.service;

import com.pamelanakano.proposta_app.http.dto.PropostaRequestDto;
import com.pamelanakano.proposta_app.http.dto.PropostaResponseDto;

import java.util.List;

public interface PropostaService {

    PropostaResponseDto criar(PropostaRequestDto requestDto);

    List<PropostaResponseDto> listarPropostas();

}
