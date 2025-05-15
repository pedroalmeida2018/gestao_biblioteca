package Vistas;

import Classes.Main;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GestaoLivros extends JFrame {
    private DefaultTableModel modeloTabela;

    public GestaoLivros() {
        setTitle("Gestão de Livros");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modeloTabela = new DefaultTableModel(new Object[]{"Título", "ISBN", "Autor", "Estado"}, 0);
        JTable tabelaLivros = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaLivros);
        add(scrollPane, BorderLayout.CENTER);

        // Carregar dados iniciais
        Main.livros.forEach(livro -> modeloTabela.addRow(livro));

        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar Livro");
        JButton btnEditar = new JButton("Editar Livro");
        JButton btnRemover = new JButton("Remover Livro");
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        add(painelBotoes, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(event -> {
            String titulo = JOptionPane.showInputDialog(this, "Título do Livro:");
            String isbn = JOptionPane.showInputDialog(this, "ISBN do Livro:");
            String autor = JOptionPane.showInputDialog(this, "Autor do Livro:");

            if (titulo != null && isbn != null && autor != null) {
                String[] novoLivro = {titulo, isbn, autor, "Disponível"};
                Main.livros.add(novoLivro);
                modeloTabela.addRow(novoLivro);
            }
        });

        btnEditar.addActionListener(event -> {
            int selectedRow = tabelaLivros.getSelectedRow();
            if (selectedRow != -1) {
                String titulo = (String) modeloTabela.getValueAt(selectedRow, 0);
                String isbn = (String) modeloTabela.getValueAt(selectedRow, 1);
                String autor = (String) modeloTabela.getValueAt(selectedRow, 2);

                titulo = JOptionPane.showInputDialog(this, "Título do Livro:", titulo);
                isbn = JOptionPane.showInputDialog(this, "ISBN do Livro:", isbn);
                autor = JOptionPane.showInputDialog(this, "Autor do Livro:", autor);

                if (titulo != null && isbn != null && autor != null) {
                    String[] livroAtualizado = {titulo, isbn, autor, "Disponível"};
                    Main.livros.set(selectedRow, livroAtualizado);
                    modeloTabela.setValueAt(titulo, selectedRow, 0);
                    modeloTabela.setValueAt(isbn, selectedRow, 1);
                    modeloTabela.setValueAt(autor, selectedRow, 2);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnRemover.addActionListener(event -> {
            int selectedRow = tabelaLivros.getSelectedRow();
            if (selectedRow != -1) {
                modeloTabela.removeRow(selectedRow);
                Main.livros.remove(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        setVisible(true);
    }
}
