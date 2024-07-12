package br.com.fiap.msclientes.DTO;

import br.com.fiap.msclientes.Model.Endereco;

import java.time.LocalDate;

public record ClienteDTO(
    long id,
    String nome,
    String email,
    String cpf,
    LocalDate nascimento,
    Endereco endereco) {
}
