package com.desafio_bradesco.Service;

import com.desafio_bradesco.model.Pagamento;
import com.desafio_bradesco.model.Proprietario;
import com.desafio_bradesco.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    Proprietario proprietario = new Proprietario();

    public void registraPagamento(Pagamento pagamento){
        if (proprietario.getSaldo() < pagamento.getValor()){
            throw new RuntimeException("Saldo insuficiente");
        } else if (pagamento.getValor() <= 0) {
            throw new RuntimeException("Impossível realizar pagamento com valor negativo");
        } else {
            double porcentagem = (pagamento.getValor() * 100) / proprietario.getSaldo();
            pagamento.setPorcentagemPagamento(porcentagem);
            pagamentoRepository.save(pagamento);
        }
    }

    public List<Pagamento> listarTodos(){
        return pagamentoRepository.findAll();
    }

    public void atualizaPagamento(Pagamento pagamento) {

        Pagamento pagamentoExistente = pagamentoRepository.findById(pagamento.getId())
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com o ID: " + pagamento.getId()));

        //Atualiza destinatário
        if (pagamento.getDestinatario() != null && !pagamento.getDestinatario().isBlank()) {
            pagamentoExistente.setDestinatario(pagamento.getDestinatario());
        }

        //Atualiza CPF
        if (pagamento.getCpf() != null && !pagamento.getCpf().isBlank()) {
            pagamentoExistente.setCpf(pagamento.getCpf());
        }

        //Atualiza Instituicao Bancaria
        if (pagamento.getInstituicaoBancaria() != null && pagamento.getInstituicaoBancaria() > 0) {
            pagamentoExistente.setInstituicaoBancaria(pagamento.getInstituicaoBancaria());
        }

        //Atualiza ChavePix
        if (pagamento.getChavePix() != null && !pagamento.getChavePix().isBlank()) {
            pagamentoExistente.setChavePix(pagamento.getChavePix());
        }

        //Atualiza Valor
        if (pagamento.getValor() != null && pagamento.getValor() > 0) {
            pagamentoExistente.setValor(pagamento.getValor());
        }

        //Atualiza Descricao
        if (pagamento.getDescrição() != null && !pagamento.getDescrição().isBlank()) {
            pagamentoExistente.setDescrição(pagamento.getDescrição());
        }

        pagamentoRepository.save(pagamentoExistente);
    }

    public void deletaPagamento(Pagamento pagamento){
        pagamentoRepository.findById(pagamento.getId()).ifPresent(pagamentoRepository::delete);
    }

}
