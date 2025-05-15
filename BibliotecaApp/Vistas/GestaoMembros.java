package Vistas;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Classes.Main;

public class GestaoMembros extends JFrame {
    private DefaultTableModel modeloTabela;

    public GestaoMembros() {
        setTitle("Gestão de Membros");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modeloTabela = new DefaultTableModel(new Object[]{"Número", "Nome", "Contacto"}, 0);
        JTable tabelaMembros = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaMembros);
        add(scrollPane, BorderLayout.CENTER);

        // Carregar dados iniciais
        Main.membros.forEach(membro -> modeloTabela.addRow(membro));

        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar Membro");
        JButton btnEditar = new JButton("Editar Membro");
        JButton btnRemover = new JButton("Remover Membro");
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        add(painelBotoes, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(event -> {
            String numero = JOptionPane.showInputDialog(this, "Número do Membro:");
            String nome = JOptionPane.showInputDialog(this, "Nome do Membro:");
            String contacto = JOptionPane.showInputDialog(this, "Contacto do Membro:");

            if (numero != null && nome != null && contacto != null) {
                String[] novoMembro = {numero, nome, contacto};
                Main.membros.add(novoMembro);
                modeloTabela.addRow(novoMembro);
            }
        });

        btnEditar.addActionListener(event -> {
            int selectedRow = tabelaMembros.getSelectedRow();
            if (selectedRow != -1) {
                String numero = (String) modeloTabela.getValueAt(selectedRow, 0);
                String nome = (String) modeloTabela.getValueAt(selectedRow, 1);
                String contacto = (String) modeloTabela.getValueAt(selectedRow, 2);

                numero = JOptionPane.showInputDialog(this, "Número do Membro:", numero);
                nome = JOptionPane.showInputDialog(this, "Nome do Membro:", nome);
                contacto = JOptionPane.showInputDialog(this, "Contacto do Membro:", contacto);

                if (numero != null && nome != null && contacto != null) {
                    String[] membroAtualizado = {numero, nome, contacto};
                    Main.membros.set(selectedRow, membroAtualizado);
                    modeloTabela.setValueAt(numero, selectedRow, 0);
                    modeloTabela.setValueAt(nome, selectedRow, 1);
                    modeloTabela.setValueAt(contacto, selectedRow, 2);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um membro para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnRemover.addActionListener(event -> {
            int selectedRow = tabelaMembros.getSelectedRow();
            if (selectedRow != -1) {
                modeloTabela.removeRow(selectedRow);
                Main.membros.remove(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um membro para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        setVisible(true);
    }
}
