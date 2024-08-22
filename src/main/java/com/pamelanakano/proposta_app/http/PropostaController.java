package com.pamelanakano.proposta_app.http;

import com.pamelanakano.proposta_app.http.dto.PropostaRequestDto;
import com.pamelanakano.proposta_app.http.dto.PropostaResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PropostaController {

    @Operation(summary = "Salva a proposta de acordo com o JSON passado por parâmetro")
    @PostMapping
    ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto propostaRequestDto);

    @Operation(summary = "Retorna todas as propostas cadastradas")
    @GetMapping
    ResponseEntity<List<PropostaResponseDto>> listarPropostas();

}
