package com.desafio_bradesco.controller;

import com.desafio_bradesco.Service.PagamentoService;
import com.desafio_bradesco.model.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/pagar")
    public void pagar(@RequestBody Pagamento pagamento){
        pagamentoService.registraPagamento(pagamento);
    }

}
