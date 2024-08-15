package com.pamelanakano.proposta_app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valorsolicitado")
    private Double valorSolicitado;

    @Column(name = "prazopagamento")
    private int prazoPagamento;

    private Boolean aprovada;

    private boolean integrada;

    private String observacao;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario")
    @JsonManagedReference
    private Usuario usuario;

}
