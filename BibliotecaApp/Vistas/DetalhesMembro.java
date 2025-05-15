package Vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;

public class DetalhesMembro extends JFrame {
    public DetalhesMembro(String[] dadosMembro, DefaultTableModel modeloTabela) {
        this(dadosMembro, modeloTabela, -1);
    }

    public DetalhesMembro(String[] dadosMembro, DefaultTableModel modeloTabela, int rowIndex) {
        setTitle(dadosMembro == null ? "Adicionar Membro" : "Editar Membro");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        JLabel lblNumero = new JLabel("Número:");
        JTextField txtNumero = new JTextField(dadosMembro != null ? dadosMembro[0] : "");
        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(dadosMembro != null ? dadosMembro[1] : "");
        JLabel lblContacto = new JLabel("Contacto:");
        JTextField txtContacto = new JTextField(dadosMembro != null ? dadosMembro[2] : "");

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        add(lblNumero);
        add(txtNumero);
        add(lblNome);
        add(txtNome);
        add(lblContacto);
        add(txtContacto);
        add(btnSalvar);
        add(btnCancelar);

        btnSalvar.addActionListener(e -> {
            String numero = txtNumero.getText();
            String nome = txtNome.getText();
            String contacto = txtContacto.getText();

            if (numero.isEmpty() || nome.isEmpty() || contacto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (rowIndex == -1) {
                modeloTabela.addRow(new Object[]{numero, nome, contacto});
            } else {
                modeloTabela.setValueAt(numero, rowIndex, 0);
                modeloTabela.setValueAt(nome, rowIndex, 1);
                modeloTabela.setValueAt(contacto, rowIndex, 2);
            }

            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
