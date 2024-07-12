package com.fiap.entrega.endereco;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.fiap.entrega.dto.EnderecoDTO;
import com.fiap.entrega.entity.Endereco;
import com.fiap.entrega.exceptions.ResourcesNotFoundException;
import com.fiap.entrega.repository.EnderecoRepository;
import com.fiap.entrega.service.EnderecoService;
import com.fiap.entrega.utils.TestHelper;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class EnderecoIntegrationTest {
    
    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private EnderecoService service;

    /**
     * Test case to verify if the table can be created.
     *
     * @return void
     */
    @Test
    void devePermitirCriarTabela() {
        assertDoesNotThrow(() -> {
            var totalRegistros = repository.count();
            assertNotNull(totalRegistros, "totalRegistros não pode ser nulo"); // Check for null pointer references
            assertThat(totalRegistros).isGreaterThanOrEqualTo(0);
        });
    }

    /**
     * Test case to verify if a new address can be inserted.
     * @throws ResourceNotFoundException 
     *
     */
    @Test
    void devePermitirInserirNovoEendereco() throws ResourcesNotFoundException {
        var novoEndereco = TestHelper.toEnderecoDTO(TestHelper.criarEndereco());
        var novoEnderecoSalvo = service.inserirEndereco(novoEndereco);
        
        assertThat(novoEnderecoSalvo).isNotNull().isInstanceOf(EnderecoDTO.class);
        assertThat(novoEnderecoSalvo.id()).isNotNull();
        assertThat(novoEnderecoSalvo.cep()).isNotNull();
        assertThat(novoEnderecoSalvo.logradouro()).isNotNull();
    }

    @Test
    void devePermitirBuscarEenderecoPorId() throws ResourcesNotFoundException {
        var novoEndereco = TestHelper.toEnderecoDTO(TestHelper.criarEndereco1());

        var novoEnderecoSalvo = service.inserirEndereco(novoEndereco);
        var novoEnderecoBuscado = service.buscarEnderecoById(novoEnderecoSalvo.id());

        assertThat(novoEnderecoBuscado).isNotNull();
        assertThat(novoEnderecoBuscado.getId()).isEqualTo(novoEnderecoSalvo.id());
    }

    /**
     * Deve permitir buscar todos os endereços.
     * @throws ResourceNotFoundException 
     */
    @Test
    void devePermitirBuscarTodosOsEenderecos() throws ResourcesNotFoundException {
        var novoEndereco = TestHelper.toEnderecoDTO(TestHelper.criarEndereco());
        service.inserirEndereco(novoEndereco);

        var novoEndereco1 = TestHelper.toEnderecoDTO(TestHelper.criarEndereco1());
        service.inserirEndereco(novoEndereco1);

        List<Endereco> enderecos = service.buscarTodosEnderecos();

        assertNotNull(enderecos);
        assertThat(enderecos).hasSize(2);
        assertThat(enderecos).isNotEmpty();
    }

    void devePermitirBuscarEenderecosPorEmail() {

        fail("Teste ainda não implementado");
    }

    /**
     * Deletes an address and tests if the address is successfully deleted.
     *
     * @throws ResourceNotFoundException if the address is not found
     */
    @Test
    void devePermitirDeletarEendereco() throws ResourcesNotFoundException {
        var novoEndereco = TestHelper.toEnderecoDTO(TestHelper.criarEndereco());
        service.inserirEndereco(novoEndereco);

        assertDoesNotThrow(() -> {
            service.deletarEndereco(novoEndereco.id());
            assertThat(repository.existsById(novoEndereco.id())).isFalse();
        });
    }   
}