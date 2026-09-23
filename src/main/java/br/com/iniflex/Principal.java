package br.com.iniflex;

import java.security.Permission;
import java.time.LocalDate;
import br.com.iniflex.model.Pessoa;

public class Principal {
    
    public static void main(String[] args) {

        // System.out.println("Teste Pratico Iniflex");

        Pessoa pessoa = new Pessoa(
            "Maria",
            LocalDate.of(2000, 10, 18)
        );

        System.out.println(pessoa.getNome());
        System.out.println(pessoa.getDataNascimento());
    }
}
