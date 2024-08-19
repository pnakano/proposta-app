package com.pamelanakano.proposta_app.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PropostaRequestDto {

    private String nome;

    private String sobrenome;

    private String telefone;

    private String cpf;

    private String email;

    private Double renda;

    private Double valorSolicitado;

    private int prazoPagamento;

}
