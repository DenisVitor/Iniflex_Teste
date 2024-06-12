package Iniflex_Teste.funcionario;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Currency;

import Iniflex_Teste.pessoa.Pessoa;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    @Override
    public String toString() {
        NumberFormat num = NumberFormat.getCurrencyInstance();
        Currency curr = Currency.getInstance("BRL");
        num.setCurrency(curr);
        return super.toString() + ", Salário: " + num.format(salario) + ", Função: " + funcao;
    }
}
