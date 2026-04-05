package com.desafio_bradesco;

import com.desafio_bradesco.Service.PagamentoService;
import com.desafio_bradesco.model.Pagamento;
import com.desafio_bradesco.model.Proprietario;
import com.desafio_bradesco.repository.PagamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private Proprietario proprietario;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    void deveRetornarPagamentoSalvo(){

        Pagamento pagamento = new Pagamento();

        pagamento.setId(UUID.randomUUID());
        pagamento.setDestinatario("Joao");
        pagamento.setCpf("10000000000");
        pagamento.setInstituicaoBancaria(101);
        pagamento.setChavePix("10000000000");
        pagamento.setValor(10.00);

        when(proprietario.getSaldo()).thenReturn(100.00);

        pagamentoService.registraPagamento(pagamento);

        verify(pagamentoRepository).save(pagamento);

    }

    @Test
    void deveRetornarSaldoInsuficiente(){

        Pagamento pagamento = new Pagamento();

        pagamento.setId(UUID.randomUUID());
        pagamento.setDestinatario("Joao");
        pagamento.setCpf("10000000000");
        pagamento.setInstituicaoBancaria(101);
        pagamento.setChavePix("10000000000");
        pagamento.setValor(100.00);

        when(proprietario.getSaldo()).thenReturn(90.00);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pagamentoService.registraPagamento(pagamento);
        });

        assertEquals("Saldo insuficiente", exception.getMessage());

    }

    @Test
    void deveRetornarImpossivel(){

        Pagamento pagamento = new Pagamento();

        pagamento.setId(UUID.randomUUID());
        pagamento.setDestinatario("Joao");
        pagamento.setCpf("10000000000");
        pagamento.setInstituicaoBancaria(101);
        pagamento.setChavePix("10000000000");
        pagamento.setValor(0.00);

        when(proprietario.getSaldo()).thenReturn(90.00);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pagamentoService.registraPagamento(pagamento);
        });

        assertEquals("Impossível realizar pagamento com valor negativo", exception.getMessage());

    }

}
