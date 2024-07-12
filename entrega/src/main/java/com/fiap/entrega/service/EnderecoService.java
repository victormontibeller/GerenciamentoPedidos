package com.fiap.entrega.service;

import com.fiap.entrega.dto.EnderecoDTO;
import com.fiap.entrega.entity.Endereco;
import com.fiap.entrega.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {  

    @Autowired
    private EnderecoRepository enderecoRepository;

    /**
     * Inserts an EnderecoDTO into the database and returns the corresponding EnderecoDTO.
     *
     * @param  EnderecoDTO  the EnderecoDTO object to be inserted
     * @return              the inserted EnderecoDTO object
     */
    public EnderecoDTO inserirEndereco(EnderecoDTO EnderecoDTO) {
        Endereco endereco = toEndereco(EnderecoDTO);

        enderecoRepository.save(endereco);
    
        return toDTO(endereco); 
    }
    
    /**
     * Retrieves an Endereco object from the enderecoRepository by its ID.
     *
     * @param  id  the ID of the Endereco object to retrieve
     * @return     the Endereco object with the specified ID, or null if not found
     */
    public Endereco buscarEnderecoById(long id) {
        return enderecoRepository.findById(id).get();
    }

    /**
     * Retrieves all the enderecos from the enderecoRepository.
     *
     * @return  a list of all the enderecos
     */
    public List<Endereco> buscarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    /**
     * Updates an existing Endereco object with the provided ID using the details from novoEndereco.
     *
     * @param  id           the ID of the Endereco object to update
     * @param  novoEndereco the new Endereco object containing updated details
     */
    public void atualizarEndereco(long id, Endereco novoEndereco) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);
        
        if (enderecoExistente.isPresent()) {
            Endereco endereco = enderecoExistente.get();

            endereco.setTipoEndereco(novoEndereco.getTipoEndereco());
            endereco.setCep(novoEndereco.getCep());
            endereco.setLogradouro(novoEndereco.getLogradouro());
            endereco.setComplemento(novoEndereco.getComplemento());
            endereco.setCidade(novoEndereco.getCidade());
            endereco.setUf(novoEndereco.getUf());
            endereco.setPais(novoEndereco.getPais());

            enderecoRepository.save(endereco);
        } else {
            throw new IllegalArgumentException("Não foi encontrado nenhum endereço com o id " + id);
        }
    }

    /**
     * Deletes an Endereco object with the given id from the database.
     *
     * @param  id  the id of the Endereco object to be deleted
     * @throws IllegalArgumentException if no Endereco object with the given id is found
     */
    public void deletarEndereco(long id) {
        Optional<Endereco> EnderecoExistente = enderecoRepository.findById(id);

        if (!EnderecoExistente.isPresent()) {
            throw new IllegalArgumentException("Não foi encontrado nenhum endereço com o id " + id);
        }

        Endereco Endereco = EnderecoExistente.get();
        enderecoRepository.delete(Endereco);
    }

    /**
     * Retrieves an Endereco object from the enderecoRepository by its CEP.
     *
     * @param  cep  the CEP of the Endereco object to retrieve
     * @return     the Endereco object with the specified CEP
     */
    public Endereco buscarEnderecoByCep(String cep) {
        return enderecoRepository.findByCep(cep);        
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
    public EnderecoDTO toDTO(Endereco Endereco) {
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

}
