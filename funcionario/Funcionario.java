package Iniflex_Teste.funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

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
        DecimalFormat decimal = new DecimalFormat();
        decimal.setMaximumFractionDigits(2);
        decimal.setGroupingUsed(false);
        return super.toString() + ", Salário: R$ " + decimal.format(salario).replace(".", ",") + ", Função: " + funcao;
    }
}
