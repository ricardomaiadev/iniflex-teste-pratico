package br.com.iniflex;

import java.math.BigDecimal;
import java.time.LocalDate;
import br.com.iniflex.model.Funcionario;

public class Principal {
    
    public static void main(String[] args) {

        // System.out.println("Teste Pratico Iniflex");

        Funcionario maria = new Funcionario(
            "Maria",
            LocalDate.of(2000, 10, 18),
            new BigDecimal("2009.44"),
            "Operador"
        );
        
        System.out.println(maria.getNome());
        System.out.println(maria.getDataNascimento());
        System.out.println(maria.getSalario());
        System.out.println(maria.getFuncao());
    }
}
