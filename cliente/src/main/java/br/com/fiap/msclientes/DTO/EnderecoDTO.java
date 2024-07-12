package br.com.fiap.msclientes.DTO;

public  record EnderecoDTO(
        long id,
        String rua,
        String numero,
        String cep,
        String complemento
) {
}
