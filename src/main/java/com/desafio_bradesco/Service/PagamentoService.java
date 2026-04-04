package com.desafio_bradesco.Service;

import com.desafio_bradesco.model.Pagamento;
import com.desafio_bradesco.model.Proprietario;
import com.desafio_bradesco.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    PagamentoRepository pagamentoRepository;
    Proprietario proprietario;

    public void registraPagamento(Pagamento pagamento){
        if (proprietario.getSaldo() < pagamento.getValor()){
            throw new RuntimeException("Saldo insuficiente");
        } else if (pagamento.getValor() <= 0) {
            throw new RuntimeException("Impossível realizar pagamento com valor negativo");
        } else {
            pagamentoRepository.save(pagamento);
        }
    }
}
