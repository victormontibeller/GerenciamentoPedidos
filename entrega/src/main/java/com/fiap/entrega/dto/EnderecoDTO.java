package com.fiap.entrega.dto;

import com.fiap.entrega.entity.TipoEndereco;

public record EnderecoDTO(    
    long id,
    TipoEndereco tipoEndereco,  
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String cidade,
    String uf,
    String pais) 
{
    
}
