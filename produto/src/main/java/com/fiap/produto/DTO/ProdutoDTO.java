package com.fiap.produto.DTO;

import java.time.LocalDate;

public record ProdutoDTO(
    long id,
    String nome,
    Double preco,
    int quantidade,
    LocalDate dataAtualizacao
) {
    public void validate() {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo ou vazio.");
        }

        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade do produto não pode ser negativa.");
        }

        if (preco <= 0) {
            throw new IllegalArgumentException("Preço do produto deve ser maior que zero.");
        }

        if (dataAtualizacao == null || dataAtualizacao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de atualização do produto inválida.");
        }
    }
}
