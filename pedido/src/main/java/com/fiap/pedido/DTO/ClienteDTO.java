package com.fiap.pedido.DTO;

import java.time.LocalDate;

public record ClienteDTO(
        long id,
        String nome,
        String email,
        String cpf,
        LocalDate nascimento){
}
