package com.fiap.pedido.Service;

import com.fiap.pedido.DTO.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteService {

    private final RestTemplate restTemplate;

    @Autowired
    public ClienteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClienteDTO buscarClientePorId(Long id) {
        String url = "http://localhost:8080/clientes/" + id;
        return restTemplate.getForObject(url, ClienteDTO.class);
    }
}