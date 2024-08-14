package com.pamelanakano.proposta_app.http;

import com.pamelanakano.proposta_app.http.dto.PropostaRequestDto;
import com.pamelanakano.proposta_app.http.dto.PropostaResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PropostaController {

    @PostMapping
    ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto propostaRequestDto);

}
