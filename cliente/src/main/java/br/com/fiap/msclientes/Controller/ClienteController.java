package br.com.fiap.msclientes.Controller;

import br.com.fiap.msclientes.DTO.ClienteDTO;
import br.com.fiap.msclientes.Excecoes.ResourceNotFoundException;
import br.com.fiap.msclientes.Model.Cliente;
import br.com.fiap.msclientes.Service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
@Tag(name = "open-api")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarClientes() throws ResourceNotFoundException {
        return ResponseEntity.ok().body(clienteService.buscarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(clienteService.buscarCliente(id));
    }

    @GetMapping("/email/{email}/")
    public ResponseEntity<Cliente> buscarClientePorEmail(@PathVariable String email) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(clienteService.buscarClientePorEmail(email));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable String cpf) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(clienteService.buscarClientePorCpf(cpf));
    }    

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Cliente> buscarClientePorNome(@PathVariable String nome) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(clienteService.buscarClientePorNome(nome));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) throws ResourceNotFoundException {
        ClienteDTO clienteSalva = clienteService.criarCliente(clienteDTO);
        return new ResponseEntity<>(clienteSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCliente(@PathVariable long id) throws ResourceNotFoundException {
        String msg = clienteService.excluirCliente(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
