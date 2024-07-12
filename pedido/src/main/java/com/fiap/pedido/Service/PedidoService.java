package com.fiap.pedido.Service;

import com.fiap.pedido.DTO.ClienteDTO;
import com.fiap.pedido.DTO.PedidoDTO;
import com.fiap.pedido.DTO.ProdutoDTO;
import com.fiap.pedido.Exceptions.ResourceNotFoundException;
import com.fiap.pedido.Model.Pedido;
import com.fiap.pedido.Model.Produto;
import com.fiap.pedido.Repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }
    
    // add
    public PedidoDTO salvarPedido(PedidoDTO pedidoDTO) throws ResourceNotFoundException {
    ClienteDTO cliente = clienteService.buscarClientePorId(pedidoDTO.clienteId());
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente não encontrado para este id : " + pedidoDTO.clienteId());
        }

        for (Produto produto : pedidoDTO.produtos()) {
            ProdutoDTO item = produtoService.buscarProduto(produto.getProdutoId());
            if (item == null) {
                throw new ResourceNotFoundException("Produto não encontrado para este id : " + produto.getProdutoId());
            }
            if (produto.getQuantidade() > item.quantidade()) {
                throw new ResourceNotFoundException("Produto sem saldo para a venda: " + produto.getProdutoId() + " Saldo: " + item.quantidade());
            }
            ResponseEntity<String> response = produtoService.atualizarQuantidadeProduto(produto.getProdutoId(), produto.getQuantidade());
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new HttpClientErrorException(response.getStatusCode(), "Não foi possível atualizar o saldo do produto: " + produto.getProdutoId());
            }
        }

        Pedido pedido = toEntity(pedidoDTO);

        try {
            pedido = pedidoRepository.save(pedido);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao inserir o pedido: " + e.getMessage());
        }

        // Retorna o novo modelo
        return toDTO(pedido);
    }

    public List<Pedido> buscarPedidos() throws ResourceNotFoundException {
        List<Pedido> pedido = pedidoRepository.findAll();
        if (pedido.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum pedido encontrado.");
        }
        return pedido;
    }

    public Pedido buscarPedido(long id) throws ResourceNotFoundException {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado para este id: " + id));
        return pedido;
    }

    // delete
    public void excluirPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public PedidoDTO toDTO(Pedido pedido) {
        // Convertendo Pedido para PedidoDTO
        return new PedidoDTO(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getValorTotal(),
                pedido.getDataCriacao(),
                pedido.getProdutos());
        }

    public Pedido toEntity(PedidoDTO PedidoDTO) {
        // Convertendo PedidoDTO para Pedido
        Pedido Pedido = new Pedido();
        Pedido.setId(PedidoDTO.id());
        Pedido.setClienteId(PedidoDTO.clienteId());
        Pedido.setValorTotal(PedidoDTO.valorTotal());
        Pedido.setDataCriacao(PedidoDTO.dataCriacao());

        List<Produto> produtos = PedidoDTO.produtos().stream()
                .map(produtoDTO -> {
                    Produto produto = new Produto();
                    produto.setId(produtoDTO.getId());
                    produto.setQuantidade(produtoDTO.getQuantidade());
                    produto.setProdutoId(produtoDTO.getProdutoId());

                    return produto;
                })
                .collect(Collectors.toList());

        Pedido.setProdutos(produtos);

        return Pedido;
    }
}
