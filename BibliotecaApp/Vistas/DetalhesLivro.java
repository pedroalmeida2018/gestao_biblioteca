package Vistas;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class DetalhesLivro extends JFrame {
    public DetalhesLivro(String[] dadosLivro, DefaultTableModel modeloTabela) {
        this(dadosLivro, modeloTabela, -1);
    }

    public DetalhesLivro(String[] dadosLivro, DefaultTableModel modeloTabela, int rowIndex) {
        setTitle(dadosLivro == null ? "Adicionar Livro" : "Editar Livro");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        JLabel lblISBN = new JLabel("ISBN:");
        JTextField txtISBN = new JTextField(dadosLivro != null ? dadosLivro[1] : "");
        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField(dadosLivro != null ? dadosLivro[0] : "");
        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField(dadosLivro != null ? dadosLivro[2] : "");

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(lblISBN);
        add(txtISBN);
        add(lblTitulo);
        add(txtTitulo);
        add(lblAutor);
        add(txtAutor);
        add(btnSalvar);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> {
            String isbn = txtISBN.getText();
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();

            if (isbn.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (rowIndex == -1) {
                modeloTabela.addRow(new Object[]{titulo, isbn, autor, "Disponível"});
            } else {
                modeloTabela.setValueAt(titulo, rowIndex, 0);
                modeloTabela.setValueAt(isbn, rowIndex, 1);
                modeloTabela.setValueAt(autor, rowIndex, 2);
            }

            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
