package com.desafio_bradesco;

import com.desafio_bradesco.Service.PagamentoService;
import com.desafio_bradesco.model.Pagamento;
import com.desafio_bradesco.model.Proprietario;
import com.desafio_bradesco.repository.PagamentoRepository;
import com.desafio_bradesco.repository.ProprietarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private ProprietarioRepository proprietarioRepository;

    @Mock
    private Proprietario proprietario;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    void deveRetornarPagamentoSalvo(){

        Proprietario proprietario = new Proprietario();
        proprietario.setId(UUID.randomUUID());
        proprietario.setNome(null);
        proprietario.setSaldo(10000000000.00);

        Pagamento pagamento = new Pagamento();

        pagamento.setId(UUID.randomUUID());
        pagamento.setDestinatario("Joao");
        pagamento.setCpf("10000000000");
        pagamento.setInstituicaoBancaria(101);
        pagamento.setChavePix("10000000000");
        pagamento.setValor(0.00);
        pagamento.setStatus("Pendente");

        pagamentoService.registraPagamento(pagamento);

        verify(pagamentoRepository).save(pagamento);

    }

    @Test
    void deveRetornarSaldoInsuficiente(){

        UUID idTeste = UUID.randomUUID();

        Proprietario proprietario = new Proprietario();
        proprietario.setId(idTeste);
        proprietario.setSaldo(50.00);

        Pagamento pagamento = new Pagamento();

        pagamento.setId(UUID.randomUUID());
        pagamento.setDestinatario("Joao");
        pagamento.setCpf("10000000000");
        pagamento.setInstituicaoBancaria(101);
        pagamento.setChavePix("10000000000");
        pagamento.setValor(100.00);
        pagamento.setStatus("Pendente");

        when(proprietarioRepository.findById(idTeste)).thenReturn(Optional.of(proprietario));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pagamentoService.registraPagamento(pagamento);
        });

        assertEquals("Saldo insuficiente", exception.getMessage());

    }

    @Test
    void deveRetornarImpossivel(){

        Proprietario proprietario = new Proprietario();
        proprietario.setId(UUID.randomUUID());
        proprietario.setNome(null);
        proprietario.setSaldo(10000000000.00);

        Pagamento pagamento = new Pagamento();

        pagamento.setId(UUID.randomUUID());
        pagamento.setDestinatario("Joao");
        pagamento.setCpf("10000000000");
        pagamento.setInstituicaoBancaria(101);
        pagamento.setChavePix("10000000000");
        pagamento.setValor(0.00);
        pagamento.setStatus("Pendente");

        pagamentoService.registraPagamento(pagamento);

        verify(pagamentoRepository).save(pagamento);

    }


}
