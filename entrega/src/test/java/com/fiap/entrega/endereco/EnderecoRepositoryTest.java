package com.fiap.entrega.endereco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.entrega.entity.Endereco;
import com.fiap.entrega.entity.TipoEndereco;
import com.fiap.entrega.repository.EnderecoRepository;
import com.fiap.entrega.service.EnderecoService;
import com.fiap.entrega.utils.TestHelper;

class EnderecoRepositoryTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    AutoCloseable openMocks;

    /**
     * A method to set up the test environment before each test.
     *
     */
    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    /**
     * Test for inserting a new address successfully.
     */
    @Test
    void inserirNovoEnderecoComSucesso() {
        Endereco endereco = TestHelper.criarEndereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        var enderecoSalvo = enderecoRepository.save(endereco);

        assertNotNull(endereco);
        assertThat(endereco).isEqualTo(enderecoSalvo);
    }

    /**
     * Test method to search for an address by ID successfully.
     */
    @Test
    void buscarEnderecoPorIdComSucesso() {
        Endereco endereco = TestHelper.criarEndereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);
        when(enderecoRepository.findById(endereco.getId())).thenReturn(Optional.of(endereco));
    
        Endereco enderecoEncontrado = enderecoService.buscarEnderecoById(endereco.getId());

        assertTrue(enderecoRepository.findById(endereco.getId()).isPresent());
        assertEquals(enderecoEncontrado, endereco);
        assertTrue(enderecoEncontrado.getId() == endereco.getId());
    }

    /**
     * Test method to search for an address by CEP successfully.
     */
    @Test
    void buscarEnderecoPorCepComSucesso(){
        Endereco endereco = TestHelper.criarEndereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);
        when(enderecoRepository.findByCep(endereco.getCep())).thenReturn(endereco);
    
        Endereco enderecoEncontrado = enderecoService.buscarEnderecoByCep(endereco.getCep());

        assertTrue(enderecoRepository.findByCep(endereco.getCep()).equals(enderecoEncontrado));
        assertEquals(enderecoEncontrado, endereco);
        assertTrue(enderecoEncontrado.getCep() == endereco.getCep());
    }

    /**
     * Test method to delete an address successfully.
     */
    @Test
    void deletarEnderecoComSucesso(){
        
        Endereco endereco = TestHelper.criarEndereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);
        when(enderecoRepository.findById(endereco.getId())).thenReturn(Optional.of(endereco));

        enderecoRepository.delete(endereco);

        verify(enderecoRepository, times(1)).delete(endereco);
    }

    /**
     * Test method to update an address successfully.
     */
    @Test
    void testeAtualizarEnderecoComSucesso() {

        Endereco endereco = TestHelper.criarEndereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);
        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

        endereco.setCep("12345-678");
        endereco.setComplemento("Casa 1");
        endereco.setCidade("São Paulo");
        endereco.setLogradouro("Rua Teste");
        endereco.setPais("Brasil");
        endereco.setUf("SP");
        endereco.setTipoEndereco(TipoEndereco.DESTINATARIO);
        
        enderecoService.atualizarEndereco(1L, endereco);

        verify(enderecoRepository, times(1)).save(endereco);
        assertEquals(TipoEndereco.DESTINATARIO, endereco.getTipoEndereco());
        assertEquals("12345-678", endereco.getCep());
        assertEquals("Rua Teste", endereco.getLogradouro());
        assertEquals("Casa 1", endereco.getComplemento());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getUf());
        assertEquals("Brasil", endereco.getPais());
    }

    


}
