package br.fiap.calculadora.spring.controller;

import br.fiap.calculadora.spring.service.CalculadoraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CalculadoraController {

    // Injeção de Dependência (Dependency Injection)
    private final CalculadoraService service;
   // Em vez de você mesmo criar o objeto (new CalculadoraService())
    // você deixa o Spring criar e entregar pra você.
    public CalculadoraController(CalculadoraService service) {

        this.service = service;
    }

    @PostMapping("/calcular")
    public String calcular(@RequestParam String valor1,
                           @RequestParam String valor2,
                           @RequestParam String operacao,
    Model model){
        // inicialmente o resultado é nullo então eu coloco o valor null
        // o erro é uma mensagem, então coloco string vazia inicialmente
        BigDecimal resultado = null;
        String erro = "";
        // faço o tratamento de exeção porque quando gera um erro
        // eu posso tratar e mostar a mensagem para o úsuario
        // antes estava mostrando um monte de mensagem de erro
        try {
            resultado = service.calcular(valor1, valor2, operacao);
        } catch (Exception e) {
            erro = e.getMessage();
        }
        // model é um objeto que carrega dados no controller para view,
        // seria como o tranporte de dados
        model.addAttribute("resultado", resultado);
        model.addAttribute("erro", erro);
        model.addAttribute("valor1", valor1);
        model.addAttribute("valor2", valor2);
        model.addAttribute("operacao", operacao);
        return "index";

    }
}
