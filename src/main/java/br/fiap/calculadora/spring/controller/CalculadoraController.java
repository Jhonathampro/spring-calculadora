package br.fiap.calculadora.spring.controller;

import br.fiap.calculadora.spring.service.CalculadoraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CalculadoraController {

    private final CalculadoraService service;

    public CalculadoraController(CalculadoraService service) {
        this.service = service;
    }

    @PostMapping("/calcular")
    public String calcular(@RequestParam String valor1,
                           @RequestParam String valor2,
                           @RequestParam String operacao,
    Model model){
        BigDecimal resultado = service.calcular(valor1, valor2, operacao);
        model.addAttribute("resultado", resultado);
        return "index";

    }
}
