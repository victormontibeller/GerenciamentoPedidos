package com.fiap.pedido.Controller;

import com.fiap.pedido.DTO.PedidoDTO;
import com.fiap.pedido.Exceptions.ResourceNotFoundException;
import com.fiap.pedido.Model.Pedido;
import com.fiap.pedido.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> buscarPedidos() throws ResourceNotFoundException {
        return ResponseEntity.ok().body(pedidoService.buscarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> buscarPedido(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(Optional.ofNullable(pedidoService.buscarPedido(id)));
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> inserirPedido(@Valid @RequestBody PedidoDTO pedidoDTO) throws ResourceNotFoundException {
        PedidoDTO pedidoSalva = pedidoService.salvarPedido(pedidoDTO);

        return new ResponseEntity<>(pedidoSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPedido(@PathVariable long id) {
        try {
            pedidoService.excluirPedido(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Não foi possível excluir o pedido!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Pedido excluido com sucesso!", HttpStatus.OK);
    }

}
