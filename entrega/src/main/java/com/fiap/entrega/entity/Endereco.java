package com.fiap.entrega.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name ="endereco")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Endereco {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private TipoEndereco tipoEndereco;/**REMETENTE ou DESTINATARIO*/

    @Column(unique = false, nullable = false)
    private String cep;

    private String logradouro;
    
    private String complemento;
    
    private String bairro;

    private String cidade;
    
    private String uf;
    
    private String pais;
}
