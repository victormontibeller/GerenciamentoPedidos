package com.fiap.entrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.entrega.dto.EnderecoDTO;
import com.fiap.entrega.entity.Endereco;
import com.fiap.entrega.exceptions.ResourcesNotFoundException;
import com.fiap.entrega.service.EnderecoService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

    public static org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    private EnderecoService enderecoService;

    //inserir endereço --OK
    @PostMapping
    public ResponseEntity<EnderecoDTO> inserirEndereco(@Valid @RequestBody EnderecoDTO EnderecoDTO) throws ResourcesNotFoundException {
        EnderecoDTO endereco = enderecoService.inserirEndereco(EnderecoDTO);

        return new ResponseEntity<EnderecoDTO>(endereco, HttpStatus.CREATED);
    }
    
    //buscar endereço --OK
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarEnderecoById(@PathVariable long id) throws ResourcesNotFoundException {
        Endereco endereco = enderecoService.buscarEnderecoById(id);
        if (endereco == null) {
            LOGGER.info("Endereço não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        LOGGER.info("Endereço {}", endereco.getId());
        return ResponseEntity.ok(endereco);
    }

    //listar todos os endereços --OK
    @GetMapping
    public ResponseEntity<List<Endereco>> buscarEnderecos() {
        return ResponseEntity.ok(enderecoService.buscarTodosEnderecos());
    }
    
    //atualizar endereço --OK
    @PutMapping("/update/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@RequestBody EnderecoDTO EnderecoDTO, @PathVariable long id) throws ResourcesNotFoundException {
        final Endereco novoEndereco = enderecoService.toEndereco(EnderecoDTO); // Transforma o enderecoDTO;
        enderecoService.atualizarEndereco(id, novoEndereco);
        return ResponseEntity.ok(novoEndereco);
    }

    //deletar endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEndereco(@PathVariable long id) throws ResourcesNotFoundException {
        enderecoService.deletarEndereco(id);
        String message = "Endereço deletado com sucesso!";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
