package com.pamelanakano.proposta_app.repository;

import com.pamelanakano.proposta_app.model.Proposta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    List<Proposta> findAllByIntegradaIsFalse();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Proposta SET aprovada = :aprovada, observacao = :observacao WHERE id = :id")
    void updateAprovadaObservacao(Long id, boolean aprovada, String observacao);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Proposta SET integrada = :integrada WHERE id = :id")
    void updatePropostaIntegrada(Long id, boolean integrada);

}
