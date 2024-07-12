package com.fiap.produto.Model;

import com.fiap.produto.Config.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data

@Entity
@Table(name = "produto")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Produto {

   @Id
   @Column(unique = true)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String nome;

   @Column(nullable = false)
   private int quantidade;    

   @Column(nullable = false)
   private Double preco;    

   //@Column(nullable = false)
   private LocalDate dataAtualizacao; 

}
