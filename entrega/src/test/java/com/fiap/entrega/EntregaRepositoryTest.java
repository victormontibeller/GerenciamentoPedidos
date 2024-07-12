package com.fiap.entrega;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.entrega.entity.Entrega;
import com.fiap.entrega.entity.StatusEntrega;
import com.fiap.entrega.repository.EntregaRepository;
import com.fiap.entrega.service.EntregaService;
import com.fiap.entrega.utils.TestHelper;

public class EntregaRepositoryTest {
    
    @Mock
    private EntregaRepository entregaRepository;

    @InjectMocks
    private EntregaService entregaService;

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

    @Test
    void devePermitirCriarNovaEntrega() {
        Entrega entrega = TestHelper.criarEntrega();
        when(entregaRepository.save(entrega)).thenReturn(entrega);

        var entregaCriada = entregaRepository.save(entrega);

        assertNotNull(entregaCriada);
        assertEquals(entrega, entregaCriada);
    }

    @Test
    void devePermitirBuscarEntregaPorId() {
        
        Entrega entrega = TestHelper.criarEntrega();
        when(entregaRepository.save(entrega)).thenReturn(entrega);
        when(entregaRepository.findById(entrega.getId())).thenReturn(Optional.of(entrega));

        var entregaEncontrada = entregaService.readEntregabyID(entrega.getId());

        assertNotNull(entregaEncontrada);
        assertEquals(entrega, entregaEncontrada);
        assertEquals(entrega.getId(), entregaEncontrada.getId());
    }

    @Test
    void devePermitirBuscarTodasEntregas() {
        Entrega entrega = TestHelper.criarEntrega();
        Entrega entrega1 = TestHelper.criarEntrega1();

        when(entregaRepository.save(entrega)).thenReturn(entrega);
        when(entregaRepository.findById(entrega.getId())).thenReturn(Optional.of(entrega));
        var entregaEncontrada = entregaService.readEntregabyID(entrega.getId());

        assertNotNull(entregaEncontrada);
        assertEquals(entrega, entregaEncontrada);

        when(entregaRepository.save(entrega1)).thenReturn(entrega1);
        when(entregaRepository.findById(entrega1.getId())).thenReturn(Optional.of(entrega1));
        var entregaEncontrada1 = entregaService.readEntregabyID(entrega1.getId());

        assertNotNull(entregaEncontrada1);
        assertEquals(entrega1, entregaEncontrada1);

        when(entregaRepository.findAll()).thenReturn(Arrays.asList(entrega, entrega1));

        List <Entrega> entregas = entregaRepository.findAll();

        assertNotNull(entregas);
        assertEquals(2, entregas.size());
    }

    @Test
    void devePermitirAtualizarEntrega() {

        Entrega entrega = TestHelper.criarEntrega();
        when(entregaRepository.save(entrega)).thenReturn(entrega);
        when(entregaRepository.findById(entrega.getId())).thenReturn(Optional.of(entrega));

        entrega.setCodigoRastreio("654987213");
        entrega.setPeso(1000.0);
        entrega.setStatusEntrega(StatusEntrega.ENVIADA);
        entrega.setValor(1200.00);

        entregaService.updateEntrega(entrega.getId(), entrega);

        assertEquals(entrega.getCodigoRastreio(), "654987213");
        assertEquals(entrega.getPeso(), 1000.0);
        assertEquals(entrega.getStatusEntrega(), StatusEntrega.ENVIADA);
        assertEquals(entrega.getValor(), 1200.00);
    }


}
