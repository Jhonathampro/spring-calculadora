package br.fiap.calculadora.spring.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculadoraService {
    public BigDecimal calcular(String valor1, String valor2,
                               String operacao){
        BigDecimal v1 = toBigDecimal(valor1);
        BigDecimal v2 = toBigDecimal(valor2);

        return  switch (operacao) {
            case "soma" -> v1.add(v2);
            case "subtracao" -> v1.subtract(v2);
            case "multiplicacao" -> v1.multiply(v2);
            case "divisao" -> {
                if(v2.compareTo(BigDecimal.ZERO)== 0){
                    throw new IllegalArgumentException("Não existe divisão por zero");
                }
                // o scale é a quantidade de casas decimais, e o rounding é
                // a regra para onde eu vou arredondar, por exemplo esse
                // é a regra de arredondamento
                yield v1.divide(v2, 4, RoundingMode.HALF_UP);
            }
            default -> throw  new IllegalArgumentException("Operação inválida");
        };
    }

    private BigDecimal toBigDecimal(String valor){
        String aux = valor.trim().replace(",", ".");
        try {
          return new BigDecimal(aux);
        } catch (Exception e) {
            throw new IllegalArgumentException("O valor deve ser númerico");
        }
    }
}
