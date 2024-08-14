package com.pamelanakano.proposta_app.repository;

import com.pamelanakano.proposta_app.model.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {
}
