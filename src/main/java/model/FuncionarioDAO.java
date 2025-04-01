package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class FuncionarioDAO {
    private String nomeArq = null;
    private ArrayList<Funcionario> funcionarios;
    
    public FuncionarioDAO(String nomeArq) {
        this.nomeArq = nomeArq;
        this.funcionarios = new ArrayList<>();
        carregarFuncionarios();
    }
    
    private void carregarFuncionarios() {
        try {
            FileReader arqEntrada = new FileReader(nomeArq);
            BufferedReader in = new BufferedReader(arqEntrada);
            
            String linha;
            while ((linha = in.readLine()) != null) {
                String[] dados = linha.split(", ");
                if (dados.length == 3) {
                    String codigo = dados[0];
                    String nome = dados[1];
                    double salario = Double.parseDouble(dados[2]);
                    
                    Funcionario func = new Funcionario(codigo, nome, salario);
                    funcionarios.add(func);
                }
            }
            
            in.close();
            arqEntrada.close();
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao carregar funcionários: " + e.getMessage());
        }
    }
    
    // Adiciona funcionário apenas a coleção em memória, pq só vai salvar quando fechar
    public void grava(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }
    
    // Método para gravar todos os funcionários no arquivo (somente ao fechar)
    public void gravaTodos() throws IOException {
        FileWriter arquivoSaida = new FileWriter(nomeArq);
        PrintWriter out = new PrintWriter(arquivoSaida);
        
        for (Funcionario funcionario : funcionarios) {
            out.println(funcionario);
        }
        
        out.close();
        arquivoSaida.close();
    }
    
    public Funcionario recupera(String codigo) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCodigo().equals(codigo)) {
                return funcionario;
            }
        }
        return null;
    }
    
    public Collection<Funcionario> recuperaTodos() {
        return funcionarios;
    }
}