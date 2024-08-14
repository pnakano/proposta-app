package com.pamelanakano.proposta_app.service;

import com.pamelanakano.proposta_app.http.dto.PropostaRequestDto;
import com.pamelanakano.proposta_app.http.dto.PropostaResponseDto;
import com.pamelanakano.proposta_app.mapper.PropostaMapper;
import com.pamelanakano.proposta_app.model.Proposta;
import com.pamelanakano.proposta_app.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropostaServiceImpl implements PropostaService {

    private final PropostaRepository propostaRepository;

    public PropostaServiceImpl(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @Override
   public PropostaResponseDto criar(PropostaRequestDto requestDto) {
        Proposta propostaPersist = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(propostaPersist);
        return PropostaMapper.INSTANCE.convertEntityToDto(propostaPersist);
   }

}
