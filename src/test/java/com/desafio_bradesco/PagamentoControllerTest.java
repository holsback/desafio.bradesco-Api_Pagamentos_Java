package com.desafio_bradesco;

import com.desafio_bradesco.Service.PagamentoService;
import com.desafio_bradesco.controller.PagamentoController;
import com.desafio_bradesco.model.Pagamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PagamentoControllerTest {

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private PagamentoController pagamentoController;

    @Test
    void deveListarTodosOsPagamentos() {

        Pagamento p1 = new Pagamento();
        p1.setDestinatario("Bruno");

        Pagamento p2 = new Pagamento();
        p2.setDestinatario("Lucas");

        List<Pagamento> listaTeste = List.of(p1, p2);

        when(pagamentoService.listarTodos()).thenReturn(listaTeste);

        List<Pagamento> listaTodos = pagamentoService.listarTodos();

        assertEquals(2, listaTodos.size());
        verify(pagamentoService, times(1)).listarTodos();
    }

    @Test
    void deveRegistrarPagamento() {

        Pagamento pagamento = new Pagamento();

        pagamento.setId(UUID.randomUUID());
        pagamento.setDestinatario("Joao");
        pagamento.setCpf("10000000000");
        pagamento.setInstituicaoBancaria(101);
        pagamento.setChavePix("10000000000");
        pagamento.setValor(10.00);

        pagamentoController.pagar(pagamento);

        verify(pagamentoService).registraPagamento(pagamento);

    }

}
