package br.com.iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Optional;

import br.com.iniflex.model.Funcionario;

public class Principal {

    private static final DateTimeFormatter FORMATO_DATA = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final BigDecimal FATOR_AUMENTO = new BigDecimal("1.10");

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    
    public static void main(String[] args) {

        List<Funcionario> funcionarios = criarFuncionarios();

        NumberFormat formatoNumero = criarFormatoNumero();
        
        removerFuncionario(funcionarios, "João");
        
        imprimirFuncionarios(funcionarios, formatoNumero);
        
        aplicarAumentoSalarial(funcionarios);
        
        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparPorFuncao(funcionarios);

        imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

        imprimirAniversariantes(funcionarios);

        imprimirFuncionarioMaisVelho(funcionarios);
        
        imprimirFuncionariosEmOrdemAlfabetica(funcionarios);

        imprimirTotalSalarios(funcionarios, formatoNumero);

        imprimirSalariosMinimos(funcionarios, formatoNumero);
        
    }

    private static List<Funcionario> criarFuncionarios() {

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
            "Coordenador"
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

        return funcionarios;

    }

    private static NumberFormat criarFormatoNumero() {

        Locale brasil = Locale.forLanguageTag("pt-BR");

        NumberFormat formatoNumero = NumberFormat.getNumberInstance(brasil);

        formatoNumero.setMinimumFractionDigits(2);
        formatoNumero.setMaximumFractionDigits(2);

        return formatoNumero;
    }

    private static void removerFuncionario(List<Funcionario> funcionarios, String nome) {

        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios, NumberFormat formatoNumero) {

        for(Funcionario funcionario : funcionarios){

            System.out.println(
                "Nome: " + funcionario.getNome()
                + " | Data de Nascimento: "
                + funcionario.getDataNascimento().format(FORMATO_DATA)
                + " | Salário: "
                + formatoNumero.format(funcionario.getSalario())
                + " | Função: "
                + funcionario.getFuncao()
            );
        }
    }

    private static void aplicarAumentoSalarial(List<Funcionario> funcionarios) {

        for(Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario
                .getSalario()
                .multiply(FATOR_AUMENTO)
                .setScale(2, RoundingMode.HALF_UP);
            
            funcionario.setSalario(novoSalario);
        }

        System.out.println("\nApós aumento de 10%:\n");
    }

    private static Map<String, List<Funcionario>> agruparPorFuncao (List<Funcionario>funcionarios) {
        return funcionarios.stream()
                .collect(
                    Collectors.groupingBy(
                        Funcionario::getFuncao,
                        LinkedHashMap::new,
                        Collectors.toList()
                    )
                );
    }

    private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {        

        System.out.println("\nFuncionários agrupados por função: \n");

        for (Map.Entry<String, List<Funcionario>> grupo : funcionariosPorFuncao.entrySet()) {
            System.out.println("\nFunção: " + grupo.getKey());

            for (Funcionario funcionario : grupo.getValue()){
                System.out.println("- " + funcionario.getNome());

            }
        }

    }

    private static void imprimirAniversariantes(List<Funcionario> funcionarios) {

        System.out.println("\nFuncionários que fazem aniversário em outubro ou dezembro: ");

        funcionarios.stream()
            .filter(funcionario -> {
                int mes = funcionario.getDataNascimento().getMonthValue();

                return mes == 10 || mes == 12;
            })
            .forEach(funcionario ->
                System.out.println(
                    funcionario.getNome()
                    + " - "
                    + funcionario.getDataNascimento().format(FORMATO_DATA)
                )
            );

    }

    private static void imprimirFuncionarioMaisVelho(List<Funcionario> funcionarios) {

        Optional<Funcionario> funcionarioMaisVelho = 
            funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento));
        
        funcionarioMaisVelho.ifPresent(funcionario -> {
            int idade = Period.between(
                funcionario.getDataNascimento(),
                LocalDate.now()
            ).getYears();


        System.out.println("\nFuncionário com maior idade: "
                + "\nNome: " + funcionario.getNome()
                + "\nIdade: " + idade 
            );
        });
    }

    private static void imprimirFuncionariosEmOrdemAlfabetica(List<Funcionario> funcionarios) {

        System.out.println("\nFuncionários em ordem alfabética: ");

        funcionarios.stream()
            .sorted(Comparator.comparing(Funcionario::getNome))
            .forEach(funcionario -> 
                System.out.println(funcionario.getNome()
                )
            );
    }

    private static BigDecimal calcularTotalSalarios(List<Funcionario> funcionarios) {

        return funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static void imprimirTotalSalarios(List<Funcionario> funcionarios, NumberFormat formatoNumero) {

        BigDecimal totalSalarios = calcularTotalSalarios(funcionarios);

        System.out.println("\nTotal dos salários: "
            + formatoNumero.format(totalSalarios)
        );
    }

    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios, NumberFormat formatoNumero) {

        System.out.println("\nQuantidade de salários minimos por funcionário: ");

        for ( Funcionario funcionario : funcionarios ) {

            BigDecimal quantidadeSalariosMinimos = 
                funcionario.getSalario()
                    .divide( 
                        SALARIO_MINIMO, 
                        2, 
                        RoundingMode.HALF_UP
                    );

        System.out.println(
                funcionario.getNome()
                + ": "
                + formatoNumero.format(quantidadeSalariosMinimos)
                + " salários minimos"
            );
        }
    }
}
