package com.fiap.entrega.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.entrega.dto.EntregaDTO;
import com.fiap.entrega.entity.Entrega;
import com.fiap.entrega.exceptions.ResourcesNotFoundException;
import com.fiap.entrega.service.EntregaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/entrega")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @PostMapping
    public  ResponseEntity<EntregaDTO> inserirEntrega(@Valid @RequestBody EntregaDTO entregaDTO) throws ResourcesNotFoundException {
        EntregaDTO entrega = entregaService.createEntrega(entregaDTO);
        
        return new ResponseEntity<>(entrega, HttpStatus.CREATED);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarEntregaById(@PathVariable long id) throws ResourcesNotFoundException {
        return ResponseEntity.ok(entregaService.readEntregabyID(id));
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> buscarTodasEntregas() {
        return ResponseEntity.ok(entregaService.buscarTodasEntregas());
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Entrega> atualizarEntrega(@RequestBody EntregaDTO entregaDTO, @PathVariable long id) throws ResourcesNotFoundException {
        final Entrega novaEntrega = entregaService.toEntrega(entregaDTO);
        entregaService.updateEntrega(id, novaEntrega);
        return ResponseEntity.ok(novaEntrega);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarEntrega(@PathVariable long id) throws ResourcesNotFoundException {
        entregaService.deleteEntrega(id);
        String message = "Entrega deletada com sucesso!";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
