package com.desafio_bradesco.controller;

import com.desafio_bradesco.Service.PagamentoService;
import com.desafio_bradesco.model.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/pagar")
    public void pagar(@RequestBody Pagamento pagamento){
        pagamentoService.registraPagamento(pagamento);
    }

    @GetMapping("/localiza")
    public ResponseEntity<List<Pagamento>> localizaPagamento(){
        List<Pagamento> pagamentos = pagamentoService.listarTodos();
        return ResponseEntity.ok(pagamentos);
    }

}
