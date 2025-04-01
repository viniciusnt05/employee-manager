package controller;

import controller.FuncionarioController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.CadastraFuncionarios;

public class AplicacaoController {
    private static AplicacaoController instance;
    private FuncionarioController funcionarioController;
    private boolean dadosSalvos = true;
    private boolean salvarAoSair = true; // Controle se deve salvar quando sair ou não
    
    private AplicacaoController() {
        funcionarioController = new FuncionarioController();
    }
    
    public static AplicacaoController getInstance() {
        if (instance == null) {
            instance = new AplicacaoController();
        }
        return instance;
    }
    
    public void iniciar() {
        // Fechamento da aplicação
        configurarFechamento();
        
        CadastraFuncionarios telaCadastro = new CadastraFuncionarios();
        configurarJanelaParaFecharPrograma(telaCadastro);
        telaCadastro.setVisible(true);
    }
    
    // Método que configura o evento de fechamento para salvar os dados
    private void configurarFechamento() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (!dadosSalvos && salvarAoSair) {
                    try {
                        funcionarioController.salvarTodos();
                        System.out.println("Dados salvos automaticamente na finalização!");
                    } catch (IOException e) {
                        System.err.println("Erro ao salvar dados: " + e.getMessage());
                    }
                } else {
                    System.out.println("Encerrando sem salvar dados.");
                }
            }
        });
    }
    
    public void configurarJanelaParaFecharPrograma(JFrame janela) {
        janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int opcao = JOptionPane.showConfirmDialog(
                    janela,
                    "Deseja salvar os dados e sair?",
                    "Confirmação de Saída",
                    JOptionPane.YES_NO_CANCEL_OPTION
                );
                
                if (opcao == JOptionPane.YES_OPTION) {
                    try {
                        funcionarioController.salvarTodos();
                        dadosSalvos = true;
                        JOptionPane.showMessageDialog(janela, "Dados salvos com sucesso!");
                        salvarAoSair = false; // Não precisa salvar de novo
                        System.exit(0);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(
                            janela,
                            "Erro ao salvar dados: " + ex.getMessage(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else if (opcao == JOptionPane.NO_OPTION) {
                    // NÃO salvar
                    salvarAoSair = false;
                    System.exit(0);
                }
            }
        });
    }
    
    public void marcarDadosModificados() {
        this.dadosSalvos = false;
    }
    
    public FuncionarioController getFuncionarioController() {
        return funcionarioController;
    }
}