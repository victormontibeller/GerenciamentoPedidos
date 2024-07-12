package com.fiap.entrega.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.entrega.dto.EntregaDTO;
import com.fiap.entrega.entity.Entrega;
import com.fiap.entrega.repository.EntregaRepository;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    /**
     * Creates a new EntregaDTO based on the provided EntregaDTO.
     *
     * @param  entregaDTO    the EntregaDTO to create the new EntregaDTO from
     * @return               the newly created EntregaDTO
     */
    public EntregaDTO createEntrega(EntregaDTO entregaDTO) {
        Entrega entrega = toEntrega(entregaDTO);
        entregaRepository.save(entrega);
        return toDTO(entrega);    
    }

    /**
     * Retrieves an Entrega object from the entregaRepository based on the provided ID.
     *
     * @param  id  the ID of the Entrega object to retrieve
     * @return     the Entrega object with the specified ID, or null if no such object exists
     */
    public Entrega readEntregabyID(long id) {
        return entregaRepository.findById(id).get();
    }

    /**
     * Retrieves all the entregas from the entrega repository.
     *
     * @return  a list of Entrega objects representing all the entregas
     */
    public List<Entrega> buscarTodasEntregas() {
        return entregaRepository.findAll();
    }

    /**
     * Updates an existing Entrega with the provided ID using the data from the new delivery.
     *
     * @param  id              the ID of the delivery to update
     * @param  novaEntrega     the new delivery data to update the existing delivery with
     * @throws IllegalArgumentException if the delivery with the provided ID does not exist
     */
    public void updateEntrega(long id, Entrega novaEntrega) {
       Optional<Entrega> entregaExistente = entregaRepository.findById(id);
        
        if(!entregaExistente.isPresent()) {
            throw new IllegalArgumentException("Entrega não encontrada");                 
        }

        Entrega entrega = entregaExistente.get();

        /* id, idCliente e codigoRastreio não podem ser alterados 
           por uma questão de rastreabilidade do entrega        */                          

        entrega.setStatusEntrega(novaEntrega.getStatusEntrega());
        entrega.setDataEnvio(novaEntrega.getDataEnvio());
        entrega.setDataPrevisaoEntrega(novaEntrega.getDataPrevisaoEntrega());
        entrega.setDataEntrega(novaEntrega.getDataEntrega());
        entrega.setRemetente(novaEntrega.getRemetente());
        entrega.setDestinatario(novaEntrega.getDestinatario());
        entrega.setPeso(novaEntrega.getPeso());
        entrega.setValor(novaEntrega.getValor());
    }
    
    /**
     * Deletes an Entrega object with the given id from the database.
     *
     * @param  id  the id of the Entrega object to be deleted
     * @throws IllegalArgumentException if no Entrega object with the given id is found
     */
    public void deleteEntrega(long id) {
        Optional<Entrega> entregaExistente = entregaRepository.findById(id);
        
        if(!entregaExistente.isPresent()) {
            throw new IllegalArgumentException("Entrega não encontrada");
        }
        entregaRepository.delete(entregaExistente.get());
    }

    /**
     * Converts an EntregaDTO object to an Entrega object.
     *
     * @param  entregaDTO  the EntregaDTO object to be converted
     * @return             the converted Entrega object
     */
    public Entrega toEntrega(EntregaDTO entregaDTO) {
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
    public EntregaDTO toDTO(Entrega entrega) {
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
