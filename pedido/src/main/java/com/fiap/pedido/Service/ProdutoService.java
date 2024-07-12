package com.fiap.pedido.Service;

import com.fiap.pedido.DTO.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProdutoService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProdutoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProdutoDTO buscarProduto(Long id) {
        String url = "http://localhost:8083/produtos/" + id;
        return restTemplate.getForObject(url, ProdutoDTO.class);
    }

    public ResponseEntity<String> atualizarQuantidadeProduto(Long id, int quantidade){
        String url = "http://localhost:8083/produtos/" + id + "/quantidade/" + quantidade;
        HttpEntity<Integer> requestEntity = new HttpEntity<>(quantidade);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }

}