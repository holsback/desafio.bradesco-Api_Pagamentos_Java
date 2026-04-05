package com.desafio_bradesco;

import com.desafio_bradesco.Service.PagamentoService;
import com.desafio_bradesco.model.Pagamento;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagamentoControllerTest {

    PagamentoService pagamentoService = mock(PagamentoService.class);

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

}
