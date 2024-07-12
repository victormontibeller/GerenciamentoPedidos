package com.fiap.pedido.DTO;

public record ItemDTO(
    long id,
    Long produtoId,    
    int quantidade
) {
    
}
