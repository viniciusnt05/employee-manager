package controller;

import java.io.IOException;
import java.util.Collection;
import model.Funcionario;
import model.FuncionarioDAO;

public class FuncionarioController {
    private FuncionarioDAO dao;
    
    public FuncionarioController() {
        dao = new FuncionarioDAO("funcionarios.txt");
    }
    
    public void grava(String codigo, String nome, double salario) throws IOException {
        // Verificação pra ver se o funcionário ja existe
        Funcionario existente = busca(codigo);
        if (existente != null) {
            throw new IOException("Já existe um funcionário com o código " + codigo);
        }
        // Se não existir, criar e salvar o novo funcionário
        Funcionario funcionario = new Funcionario(codigo, nome, salario);
        dao.grava(funcionario);
        
        // Marcar que os dados foram modificados
        AplicacaoController.getInstance().marcarDadosModificados();
    }
    
    public Funcionario busca(String codigo) {
        return dao.recupera(codigo);
    }
    
    public Collection<Funcionario> listarTodos() {
        return dao.recuperaTodos();
    }
    
    public void salvarTodos() throws IOException {
        dao.gravaTodos();
    }
}