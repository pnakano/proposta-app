package com.pamelanakano.proposta_app.service;

import com.pamelanakano.proposta_app.http.dto.PropostaRequestDto;
import com.pamelanakano.proposta_app.http.dto.PropostaResponseDto;
import com.pamelanakano.proposta_app.mapper.PropostaMapper;
import com.pamelanakano.proposta_app.model.Proposta;
import com.pamelanakano.proposta_app.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaServiceImpl implements PropostaService {

    private final PropostaRepository propostaRepository;

    private final NotificacaoRabbitService notificacaoRabbitService;

    private final String propostaPendenteExchange;

    public PropostaServiceImpl(PropostaRepository propostaRepository,
                               NotificacaoRabbitService notificacaoRabbitService,
                               @Value("${rabbitmq.propostapendente.exchange}") String propostaPendenteExchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.propostaPendenteExchange = propostaPendenteExchange;
    }

    @Override
   public PropostaResponseDto criar(PropostaRequestDto requestDto) {
        Proposta propostaPersist = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        propostaRepository.save(propostaPersist);
        notificarRabbitMQ(propostaPersist);

        return PropostaMapper.INSTANCE.convertEntityToDto(propostaPersist);
   }

    @Override
    public List<PropostaResponseDto> listarPropostas() {
        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
    }

    private void notificarRabbitMQ(Proposta proposta){
        try {
            notificacaoRabbitService.notificar(proposta, propostaPendenteExchange);
        } catch (RuntimeException e) {
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

}
