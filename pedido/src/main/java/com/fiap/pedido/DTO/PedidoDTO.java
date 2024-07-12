package com.fiap.pedido.DTO;

import com.fiap.pedido.Model.Produto;

import java.time.LocalDate;
import java.util.List;

public record PedidoDTO(
    Long id,
    Long clienteId,
    Double valorTotal,
    LocalDate dataCriacao,
    List<Produto> produtos) {

      public void validate() {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do pedido deve ser maior que zero.");
        }

        if (clienteId == null) {
            throw new IllegalArgumentException("Cliente do pedido não pode ser nulo.");
        }

        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("Lista de itens do pedido não pode ser nula ou vazia.");
        }

        for (Produto produtos : produtos) {
            if (produtos == null) {
                throw new IllegalArgumentException("Item do pedido não pode ser nulo.");
            }
        }
//
//        if (quantidade <= 0) {
//            throw new IllegalArgumentException("Quantidade do pedido deve ser maior que zero.");
//        }

        if (valorTotal < 0) {
            throw new IllegalArgumentException("Valor total do pedido não pode ser negativo.");
        }

        if (dataCriacao == null || dataCriacao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de criação do pedido inválida.");
        }
    }

}
