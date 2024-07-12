package com.fiap.produto.Service;

import com.fiap.produto.DTO.ProdutoDTO;
import com.fiap.produto.Exceptions.GlobalExceptionHandler;
import com.fiap.produto.Exceptions.ResourceNotFoundException;
import com.fiap.produto.Model.Produto;
import com.fiap.produto.Repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private static final Logger logger = Logger.getLogger(ProdutoService.class.getName());

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    // add
    public ProdutoDTO salvarProduto(ProdutoDTO ProdutoDTO) throws ResourceNotFoundException {
       Produto produto = toEntity(ProdutoDTO);

        try {
            produto = produtoRepository.save(produto);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Erro ao inserir o produto: " + e.getMessage());
        }

        // Retorna o novo modelo
       return toDTO(produto);
    }

    public List<Produto> buscarProdutos() throws ResourceNotFoundException {
        List<Produto> produto = produtoRepository.findAll();
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum produto encontrado.");
        }
        return produto;
    }

    public Produto buscarProduto(long id) throws ResourceNotFoundException {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para este id: " + id));
        return produto;
    }

    // delete
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public String baixarEstoque(long id, int quantidade) throws ResourceNotFoundException {
            if (quantidade < 1) {
                // Quantidade deve ser maior que zero
                throw new IllegalArgumentException("Quantidade deve ser maior que zero");
            }

            Optional<Produto> produtoOpt = produtoRepository.findById(id);
            logger.info("---- id: " + id + " qtd: " + quantidade);

            if (produtoOpt.isEmpty()) {
                // Produto não encontrado
                throw new ResourceNotFoundException("Produto não encontrado");
            }

            Produto produto = produtoOpt.get();
            int qtdDisponivel = produto.getQuantidade();

            if (qtdDisponivel < quantidade) {
                // Quantidade disponível insuficiente
                throw new IllegalArgumentException("Quantidade maior que a disponível");
            }

            int novaQuantidade = qtdDisponivel - quantidade;
            produto.setQuantidade(novaQuantidade);

            // Atualiza o produto
            ProdutoDTO produtoDTO = this.toDTO(produto);

            this.salvarProduto(produtoDTO);

            // Retorna o produto atualizado
            return "Baixa efetuada com sucesso";
    }

    public ProdutoDTO toDTO(Produto Produto) {
        // Convertendo Produto para ProdutoDTO
        return new ProdutoDTO(
                Produto.getId(),
                Produto.getNome(),
                Produto.getPreco(),
                Produto.getQuantidade(),
                Produto.getDataAtualizacao());
    }

    public Produto toEntity(ProdutoDTO ProdutoDTO) {
        // Convertendo ProdutoDTO para Produto
        Produto Produto = new Produto();
        Produto.setId(ProdutoDTO.id());
        Produto.setNome(ProdutoDTO.nome());
        Produto.setPreco(ProdutoDTO.preco());
        Produto.setQuantidade(ProdutoDTO.quantidade());
        Produto.setDataAtualizacao(ProdutoDTO.dataAtualizacao());

        return Produto;
    }
}
