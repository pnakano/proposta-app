package com.pamelanakano.proposta_app.http;

import com.pamelanakano.proposta_app.http.dto.PropostaRequestDto;
import com.pamelanakano.proposta_app.http.dto.PropostaResponseDto;
import com.pamelanakano.proposta_app.service.interfaces.PropostaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proposta")
public class PropostaControllerImpl implements PropostaController{

    private final PropostaService propostaService;

    public PropostaControllerImpl(PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PropostaResponseDto> criar(@RequestBody PropostaRequestDto requestDto) {
        PropostaResponseDto response = propostaService.criar(requestDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri())
                .body(response);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PropostaResponseDto>> listarPropostas() {
        return ResponseEntity.ok(propostaService.listarPropostas());
    }


}
