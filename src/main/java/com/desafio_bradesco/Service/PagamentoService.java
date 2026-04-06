package com.desafio_bradesco.Service;

import com.desafio_bradesco.model.Pagamento;
import com.desafio_bradesco.model.Proprietario;
import com.desafio_bradesco.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Pagamento> listarTodos(){
        return pagamentoRepository.findAll();
    }

    public void atualizaPagamento(Pagamento pagamento){
        //Altera destinatário
        if (pagamento.getDestinatario() != null && !pagamento.getDestinatario().isBlank()) {
            pagamento.setDestinatario(pagamento.getDestinatario());
        }
        //Altera CPF
        if (pagamento.getCpf() != null && !pagamento.getCpf().isBlank()) {
            pagamento.setCpf(pagamento.getCpf());
        }
        //Altera instinuicao bancaria
        if (pagamento.getInstituicaoBancaria() > 0) {
            pagamento.setDestinatario(pagamento.getDestinatario());
        } else  {
            throw new RuntimeException("Instituição bancária inexistente");
        }
        //Altera Chave PIX
        if (pagamento.getChavePix() != null && !pagamento.getChavePix().isBlank()) {
            pagamento.setChavePix(pagamento.getChavePix());
        }
        //Altera Valor Pagamento
        if (pagamento.getValor() > 0) {
            pagamento.setValor(pagamento.getValor());
        }
        //Altera descrição
        if (pagamento.getDescrição() != null && !pagamento.getDescrição().isBlank()) {
            pagamento.setDescrição(pagamento.getDescrição());
        }
    }

    public void deletaPagamento(Pagamento pagamento){
        pagamentoRepository.findById(pagamento.getId()).ifPresent(pagamentoRepository::delete);
    }

}
