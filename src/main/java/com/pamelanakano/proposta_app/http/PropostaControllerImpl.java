package com.pamelanakano.proposta_app.http;

import com.pamelanakano.proposta_app.http.dto.PropostaRequestDto;
import com.pamelanakano.proposta_app.http.dto.PropostaResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposta")
public class PropostaControllerImpl implements PropostaController{

    @Override
    public ResponseEntity<PropostaResponseDto> save(@RequestBody PropostaRequestDto propostaRequestDto) {
        return null;
    }
}
