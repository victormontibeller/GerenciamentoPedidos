package br.com.fiap.msclientes.Controller;

import br.com.fiap.msclientes.DTO.EnderecoDTO;
import br.com.fiap.msclientes.Excecoes.ResourceNotFoundException;
import br.com.fiap.msclientes.Model.Endereco;
import br.com.fiap.msclientes.Service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> buscarEnderecos() throws ResourceNotFoundException {
        return ResponseEntity.ok().body(enderecoService.buscarEnderecos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarEndereco(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(enderecoService.buscarEndereco(id));
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<Endereco> buscarEnderecoPorCep(@PathVariable String cep) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(enderecoService.buscarEnderecoPorCep(cep));
    }    

    @PostMapping
    public ResponseEntity<EnderecoDTO> inserirEndereco(@Valid @RequestBody EnderecoDTO enderecoDTO) throws ResourceNotFoundException {
        EnderecoDTO enderecoSalva = enderecoService.criarEndereco(enderecoDTO);
        return new ResponseEntity<>(enderecoSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirEndereco(@PathVariable long id) throws ResourceNotFoundException {
        String msg = enderecoService.excluirEndereco(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
