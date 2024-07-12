package com.fiap.pedido.Model;

import com.fiap.pedido.Config.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Data

@Entity
@Table(name = "pedido")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Pedido {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true)
   private Long id;

   @JoinColumn(name = "cliente_id", nullable = false)
   private Long clienteId;

   @Column(nullable = false)
   private Double valorTotal;    

   @Column(nullable = false)
   private LocalDate dataCriacao;

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "pedido_id")
   private List<Produto> produtos;
}
