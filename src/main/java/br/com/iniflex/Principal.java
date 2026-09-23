package br.com.iniflex;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.iniflex.model.Funcionario;

public class Principal {
    
    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario(
            "Maria",
            LocalDate.of(2000, 10, 18),
            new BigDecimal("2009.44"),
            "Operador"
        ));

        funcionarios.add(new Funcionario(
            "João",
            LocalDate.of(1990, 5, 12),
            new BigDecimal("2284.38"),
            "Operador"
        ));
        funcionarios.add(new Funcionario(
            "Caio",
            LocalDate.of(1961, 5, 2),
            new BigDecimal("9836.14"),
            "Coordernador"
        ));
        funcionarios.add(new Funcionario(
            "Miguel",
            LocalDate.of(1988, 10, 14),
            new BigDecimal("19119.88"),
            "Diretor"
        ));
        funcionarios.add(new Funcionario(
            "Alice",
            LocalDate.of(1995, 1, 5),
            new BigDecimal("2234.68"),
            "Recepcionista"
        ));
        funcionarios.add(new Funcionario(
            "Heitor",
            LocalDate.of(1999, 11, 19),
            new BigDecimal("1582.72"),
            "Operador"
        ));
        funcionarios.add(new Funcionario(
            "Arthur",
            LocalDate.of(1993, 3, 31),
            new BigDecimal("4071.84"),
            "Contador"
        ));
        funcionarios.add(new Funcionario(
            "Laura",
            LocalDate.of(1994, 7, 8),
            new BigDecimal("3017.45"),
            "Gerente"
        ));
        funcionarios.add(new Funcionario(
            "Heloísa",
            LocalDate.of(2003, 5, 24),
            new BigDecimal("1606.85"),
            "Eletricista"
        ));
        funcionarios.add(new Funcionario(
            "Helena",
            LocalDate.of(1996, 9, 2),
            new BigDecimal("2799.93"),
            "Gerente"
        ));

        funcionarios.removeIf(
            funcionario -> funcionario.getNome().equals("João")
        );

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Locale brasil = Locale.forLanguageTag("pt-BR");

        java.text.NumberFormat formatoNumero = java.text.NumberFormat.getNumberInstance(brasil);

        formatoNumero.setMinimumFractionDigits(2);
        formatoNumero.setMaximumFractionDigits(2);

        System.out.println("\nQuantidade de funcionários: " + funcionarios.size() + " \n");

        for(Funcionario funcionario : funcionarios){

            System.out.println(
                "Nome: " + funcionario.getNome()
                + " | Data de Nascimento: "
                + funcionario.getDataNascimento().format(formatoData)
                + " | Salário: "
                + formatoNumero.format(funcionario.getSalario())
                + " | Função: "
                + funcionario.getFuncao()
            );
        }

        BigDecimal percentualAumento = new BigDecimal("1.10");

        for(Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario
                .getSalario()
                .multiply(percentualAumento)
                .setScale(2, RoundingMode.HALF_UP);
            
            funcionario.setSalario(novoSalario);
        }

        System.out.println("\nApós aumento de 10%:\n");

        for(Funcionario funcionario : funcionarios) {
            System.out.println(
                funcionario.getNome()
                + ": "
                + formatoNumero.format(funcionario.getSalario())
            );
        }

        Map<String, List<Funcionario>> funcionariosPorFuncao = 
            funcionarios.stream()
                .collect(
                    Collectors.groupingBy(
                        Funcionario::getFuncao,
                        LinkedHashMap::new,
                        Collectors.toList()
                    )
                );
        

        System.out.println("\nFuncionários agrupados por função: \n");

        for (Map.Entry<String, List<Funcionario>> grupo : funcionariosPorFuncao.entrySet()) {
            System.out.println("\nFunção: " + grupo.getKey());

            for (Funcionario funcionario : grupo.getValue()){
                System.out.println("- " + funcionario.getNome());

            };
        }

    }
}
