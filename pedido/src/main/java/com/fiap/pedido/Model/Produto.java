package com.fiap.pedido.Model;

import com.fiap.pedido.Config.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "produto")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @JoinColumn(name = "produto_id", nullable = false)
    private Long produtoId;

    @Column(nullable = false)
    private int quantidade;

}

