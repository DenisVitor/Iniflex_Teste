package Iniflex_Teste;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Iniflex_Teste.funcionario.Funcionario;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = inserirFuncionarios();
        Funcionarios(funcionarios);
        removerFuncionario(funcionarios, "João");
        aumentarSalarios(funcionarios, new BigDecimal("0.10"));
        FuncionariosPorFuncao(funcionarios);
        AniversariantesMes(funcionarios, 10);
        AniversariantesMes(funcionarios, 12);
        funcionarioMaisVelho(funcionarios);
        FuncionariosOrdemAlfabetica(funcionarios);
        totalSalarios(funcionarios);
        SalariosMinimos(funcionarios, new BigDecimal("1212.00"));
    }

    public static List<Funcionario> inserirFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordernador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios
                .add(new Funcionario("Alice", LocalDate.of(1995, 1, 05), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios
                .add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        return funcionarios;
    }

    public static void Funcionarios(List<Funcionario> funcionarios) {
        System.out.println("\nFuncionários:");
        funcionarios.forEach(System.out::println);
    }

    public static void removerFuncionario(List<Funcionario> funcionarios, String nome) {
        System.out.println("\nFuncionários sem o funcionário João:");
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
        funcionarios.forEach(System.out::println);
    }

    public static void aumentarSalarios(List<Funcionario> funcionarios, BigDecimal percentual) {
        System.out.println("\nFuncionários com o aumento de 10%:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(BigDecimal.ONE.add(percentual));
            funcionario.setSalario(novoSalario);
        }
        ;
        funcionarios.forEach(System.out::println);
    }

    public static void funcionarioMaisVelho(List<Funcionario> funcionarios) {
        Funcionario maisVelho = funcionarios.get(0);
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().isBefore(maisVelho.getDataNascimento())) {
                maisVelho = funcionario;
            }
        }
        System.out.println("\nO Funcionário mais velho: " + maisVelho);
    }

    public static void FuncionariosPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {

            funcionariosPorFuncao.computeIfAbsent(funcionario.getFuncao(), k -> new ArrayList<>()).add(funcionario);
        }
        System.out.println("\nFuncionários por Função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            System.out.println("\nLista de Funcionários: ");
            lista.forEach(System.out::println);
        });
    }

    public static void AniversariantesMes(List<Funcionario> funcionarios, int mes) {
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == mes)
                .collect(Collectors.toList());

        if (aniversariantes.isEmpty()) {
            System.out.println("\nNão há funcionários que fazem aniversário no mês " + mes + ".");
        } else {
            System.out.println("\nFuncionários que fazem aniversário no mês " + mes + ":");
            aniversariantes.forEach(System.out::println);
        }
    }

    public static void FuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
        System.out.println("\nFuncionários em Ordem Alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);
    }

    public static void totalSalarios(List<Funcionario> funcionarios) {
        BigDecimal total = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            total = total.add(funcionario.getSalario());
        }
        NumberFormat num = NumberFormat.getCurrencyInstance();
        Currency curr = Currency.getInstance("BRL");
        num.setCurrency(curr);
        System.out.println("\nTotal do salário de todos os funcionários: " + num.format(total));
    }

    public static void SalariosMinimos(List<Funcionario> funcionarios, BigDecimal salarioMinimo) {
        DecimalFormat decimal = new DecimalFormat();
        decimal.setMaximumFractionDigits(2);
        System.out.println(
                "\nSalários em relação ao salário mínimo (" + salarioMinimo.toString().replace(".", ",") + "):");
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioMinimoQuantidade = funcionario.getSalario().divide(salarioMinimo, RoundingMode.HALF_DOWN);
            System.out
                    .println(funcionario.getNome() + ": " + decimal.format(salarioMinimoQuantidade).replace(",", "."));
        });
    }
}
