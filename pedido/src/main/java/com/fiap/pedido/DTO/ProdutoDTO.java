package com.fiap.pedido.DTO;

import java.time.LocalDate;

public record ProdutoDTO(
        long id,
        long produtoId,
        int quantidade
) {
}