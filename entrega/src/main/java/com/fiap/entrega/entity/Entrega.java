package com.fiap.entrega.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name ="entrega")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Entrega {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;//ok

    private long idCliente;//ok

    private String codigoRastreio;//ok

    private StatusEntrega statusEntrega; //ok

    private LocalDate dataEnvio;//ok

    private LocalDate dataPrevisaoEntrega;//ok

    private LocalDate dataEntrega;

    @ManyToOne(cascade = CascadeType.ALL)
    private Endereco remetente;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Endereco destinatario;

    private double peso;

    private double valor;

}
