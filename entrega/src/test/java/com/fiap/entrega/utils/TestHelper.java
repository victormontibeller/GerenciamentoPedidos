package com.fiap.entrega.utils;

import java.time.LocalDate;

import com.fiap.entrega.dto.EnderecoDTO;
import com.fiap.entrega.dto.EntregaDTO;
import com.fiap.entrega.entity.Endereco;
import com.fiap.entrega.entity.Entrega;
import com.fiap.entrega.entity.StatusEntrega;
import com.fiap.entrega.entity.TipoEndereco;


public class TestHelper {

    /**
     * Creates and returns a new Endereco object with specific attributes.
     *
     * @return          the created Endereco object
     */
    public static Endereco criarEndereco() {
      return new Endereco(1L, 
                          TipoEndereco.DESTINATARIO,
                          "00000-000",
                          "rua almondega 9009",
                          "apto 9009",
                          "centro",
                          "sao paulo",
                          "sp",
                          "brasil");  
    }

    /**
     * Creates and returns a new Endereco object with specific attributes.
     *
     * @return          the created Endereco object
     */
    public static Endereco criarEndereco1() {
        return new Endereco(2L, 
                            TipoEndereco.REMETENTE,
                            "00000-000",
                            "rua almondega 9008",
                            "NA",
                            "centro",
                            "sao paulo",
                            "sp",
                            "brasil");  
      }
    /**
     * Converts an EnderecoDTO object to an Endereco object.
     *
     * @param  EnderecoDTO  the EnderecoDTO object to be converted
     * @return              the converted Endereco object
     */
    public Endereco toEndereco(EnderecoDTO EnderecoDTO) {
        return new Endereco(EnderecoDTO.id(),
                            EnderecoDTO.tipoEndereco(),
                            EnderecoDTO.cep(),
                            EnderecoDTO.logradouro(),
                            EnderecoDTO.complemento(),
                            EnderecoDTO.bairro(),
                            EnderecoDTO.cidade(),
                            EnderecoDTO.uf(),
                            EnderecoDTO.pais()); 
    }

    /**
     * Converts an Endereco object to an EnderecoDTO object.
     *
     * @param  Endereco  the Endereco object to be converted
     * @return           the converted EnderecoDTO object
     */
    public static EnderecoDTO toEnderecoDTO(Endereco Endereco) {
        return new EnderecoDTO(Endereco.getId(),
                                Endereco.getTipoEndereco(),
                                Endereco.getCep(),
                                Endereco.getLogradouro(),
                                Endereco.getComplemento(),
                                Endereco.getBairro(),
                                Endereco.getCidade(),
                                Endereco.getUf(),
                                Endereco.getPais());
    }


    /**
     * Creates and returns a new Entrega with specific details.
     *
     * @return         the newly created delivery
     */
    public static Entrega criarEntrega() {
        
        return new Entrega(2L, 
                            1L,
                            "123456789",
                            StatusEntrega.ENTREGUE,
                            LocalDate.now(),
                            LocalDate.now().plusDays(3),
                            LocalDate.now().plusDays(2),
                            criarEndereco(),
                            criarEndereco1(),
                            1.0,
                            1.0);
    }

    /**
     * Creates a new Entrega with specific details.
     *
     * @return         the newly created delivery
     */
    public static Entrega criarEntrega1() {
        
        return new Entrega(1L, 
                            2L,
                            "112323456789",
                            StatusEntrega.CRIADA,
                            LocalDate.now(),
                            LocalDate.now().plusDays(3),
                            LocalDate.now().plusDays(2),
                            criarEndereco(),
                            criarEndereco1(),
                            2.0,
                            100.0);
    }

    /**
     * Converts an EntregaDTO object to an Entrega object.
     *
     * @param  entregaDTO  the EntregaDTO object to be converted
     * @return             the converted Entrega object
     */
    public static Entrega toEntrega(EntregaDTO entregaDTO) {
        return new Entrega(entregaDTO.id(),
                entregaDTO.idCliente(),
                entregaDTO.codigoRastreio(),
                entregaDTO.statusEntrega(),
                entregaDTO.dataEnvio(),
                entregaDTO.dataPrevisaoEntrega(),
                entregaDTO.dataEntrega(),
                entregaDTO.Remetente(),
                entregaDTO.Destinatario(),
                entregaDTO.peso(),
                entregaDTO.valor());
    }

    /**
     * Converts an Entrega object to an EntregaDTO object.
     *
     * @param  entrega  the Entrega object to be converted
     * @return          the converted EntregaDTO object
     */
    public static EntregaDTO toEntregaDTO(Entrega entrega) {
        return new EntregaDTO(entrega.getId(),
                entrega.getIdCliente(),
                entrega.getCodigoRastreio(),
                entrega.getStatusEntrega(),
                entrega.getDataEnvio(),
                entrega.getDataPrevisaoEntrega(),
                entrega.getDataEntrega(),
                entrega.getRemetente(),
                entrega.getDestinatario(),
                entrega.getPeso(),
                entrega.getValor());
    }



}
