package model;
import java.util.Objects;

public class Funcionario {
    private String codigo;
    private String nome;
    private double salario;
    
    public Funcionario(String codigo, String nome, double salario) {
        this.codigo = codigo;
        this.nome = nome;
        this.salario = salario;
    }
    
    public Funcionario() {
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    @Override
    public String toString() {
        return codigo + ", " + nome + ", " + salario;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Funcionario other = (Funcionario) obj;
        return Objects.equals(this.codigo, other.codigo);
    }
}