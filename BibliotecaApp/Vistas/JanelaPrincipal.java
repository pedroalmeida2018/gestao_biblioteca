package Vistas;

import Classes.Main;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class JanelaPrincipal extends JFrame {
    private final JTextArea areaEmprestimos;
    private final JTextArea areaDevolucoes;

    public JanelaPrincipal() {
        Main.inicializarDados();
        
        setTitle("Biblioteca - Janela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuMembros = new JMenu("Membros");
        JMenu menuLivros = new JMenu("Livros");
        JMenu menuEmprestimos = new JMenu("Empréstimos");
        JMenu menuExit = new JMenu("Exit");
        menuBar.add(menuMembros);
        menuBar.add(menuLivros);
        menuBar.add(menuEmprestimos);
        menuBar.add(menuExit);
        setJMenuBar(menuBar);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(2, 1));
        areaEmprestimos = new JTextArea("Empréstimos Recentes");
        areaDevolucoes = new JTextArea("Devoluções Próximas");
        painelCentral.add(new JScrollPane(areaEmprestimos));
        painelCentral.add(new JScrollPane(areaDevolucoes));
        add(painelCentral, BorderLayout.CENTER);

        SwingUtilities.invokeLater(this::atualizarPainelCentral);

        menuLivros.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                setVisible(false);
                GestaoLivros gestaoLivros = new GestaoLivros();
                gestaoLivros.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        setVisible(true);
                    }
                });
            }
            @Override
            public void menuDeselected(MenuEvent e) {}
            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        menuMembros.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                setVisible(false);
                GestaoMembros gestaoMembros = new GestaoMembros();
                gestaoMembros.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        setVisible(true);
                    }
                });
            }
            @Override
            public void menuDeselected(MenuEvent e) {}
            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        menuEmprestimos.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                GestaoEmprestimos gestaoEmprestimos = new GestaoEmprestimos();
                gestaoEmprestimos.setVisible(true);
                gestaoEmprestimos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        menuExit.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) { System.exit(0); }
            @Override
            public void menuDeselected(MenuEvent e) {}
            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        setVisible(true);
    }

    public void atualizarPainelCentral() {
        String emprestimosTexto = "Empréstimos Recentes:\n";
        String devolucoesTexto = "Devoluções Próximas:\n";

        for (String[] emprestimo : Main.getEmprestimos()) {
            emprestimosTexto += String.format("Livro: %s, Membro: %s, Estado: %s, Empréstimo: %s, Retorno: %s\n",
                emprestimo[0], emprestimo[1], emprestimo[2], emprestimo[3], emprestimo[4]);
        }

        areaEmprestimos.setText(emprestimosTexto);
        areaDevolucoes.setText(devolucoesTexto);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JanelaPrincipal());
    }
}
