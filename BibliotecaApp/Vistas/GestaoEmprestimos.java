package Vistas;

import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Classes.Main;

public class GestaoEmprestimos extends JFrame {
    private DefaultTableModel modeloTabela;

    public GestaoEmprestimos() {
        setTitle("Gestão de Empréstimos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Painel Novo Empréstimo
        JPanel painelNovo = new JPanel();
        painelNovo.setBorder(BorderFactory.createTitledBorder("Novo Empréstimo"));
        painelNovo.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelNovo.add(new JLabel("Membro"));
        JComboBox<String> comboMembro = new JComboBox<>();
        Main.membros.forEach(m -> comboMembro.addItem(m[0]));
        painelNovo.add(comboMembro);
        painelNovo.add(new JLabel("Livro"));
        JComboBox<String> comboLivro = new JComboBox<>();
        Main.livros.forEach(l -> { if (l[3].equals("Disponível")) comboLivro.addItem(l[0]); });
        painelNovo.add(comboLivro);
        painelNovo.add(new JLabel("Data Entrega"));
        JTextField txtDataEntrega = new JTextField(10);
        painelNovo.add(txtDataEntrega);
        JButton btnValidarEmprestimo = new JButton("Validar");
        painelNovo.add(btnValidarEmprestimo);

        // Painel Histórico de Empréstimos
        JPanel painelHistorico = new JPanel(new BorderLayout(5, 5));
        painelHistorico.setBorder(BorderFactory.createTitledBorder("Histórico de Empréstimos"));
        JPanel painelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelFiltro.add(new JLabel("Filtro"));
        JTextField txtFiltro = new JTextField(8);
        painelFiltro.add(txtFiltro);
        painelFiltro.add(new JLabel("Membro"));
        JComboBox<String> comboFiltroMembro = new JComboBox<>();
        comboFiltroMembro.addItem("");
        Main.membros.forEach(m -> comboFiltroMembro.addItem(m[0]));
        painelFiltro.add(comboFiltroMembro);
        JButton btnProcurar = new JButton("Procurar");
        painelFiltro.add(btnProcurar);
        painelHistorico.add(painelFiltro, BorderLayout.NORTH);
        modeloTabela = new DefaultTableModel(new Object[]{"Livro", "Membro", "Estado", "Empréstimo", "Retorno"}, 0);
        JTable tabela = new JTable(modeloTabela);
        painelHistorico.add(new JScrollPane(tabela), BorderLayout.CENTER);
        JButton btnExportar = new JButton("Exportar para Ficheiro");
        JPanel painelExportar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelExportar.add(btnExportar);
        painelHistorico.add(painelExportar, BorderLayout.SOUTH);

        // Painel Devolução de Empréstimo
        JPanel painelDevolucao = new JPanel();
        painelDevolucao.setBorder(BorderFactory.createTitledBorder("Devolução de Empréstimo"));
        painelDevolucao.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelDevolucao.add(new JLabel("Membro"));
        JComboBox<String> comboDevMembro = new JComboBox<>();
        Main.membros.forEach(m -> comboDevMembro.addItem(m[0]));
        painelDevolucao.add(comboDevMembro);
        painelDevolucao.add(new JLabel("Livro"));
        JComboBox<String> comboDevLivro = new JComboBox<>();
        Main.livros.forEach(l -> comboDevLivro.addItem(l[0]));
        painelDevolucao.add(comboDevLivro);
        JButton btnValidarDevolucao = new JButton("Validar");
        painelDevolucao.add(btnValidarDevolucao);

        // Layout principal
        JPanel painelMain = new JPanel();
        painelMain.setLayout(new BoxLayout(painelMain, BoxLayout.Y_AXIS));
        painelMain.add(painelNovo);
        painelMain.add(painelHistorico);
        painelMain.add(painelDevolucao);
        add(painelMain, BorderLayout.CENTER);

        // Popular tabela
        Main.getEmprestimos().forEach(emp -> modeloTabela.addRow(emp));

        // Listeners básicos (sem lógica de negócio detalhada)
        btnValidarEmprestimo.addActionListener(e -> {
            String membro = (String) comboMembro.getSelectedItem();
            String livro = (String) comboLivro.getSelectedItem();
            String data = txtDataEntrega.getText();
            if (membro != null && livro != null && !data.isEmpty()) {
                String[] novo = {livro, membro, "Ativo", LocalDate.now().toString(), data};
                Main.getEmprestimos().add(novo);
                modeloTabela.addRow(novo); // Adiciona ao histórico imediatamente
                comboLivro.removeItem(livro);
                JOptionPane.showMessageDialog(this, "Empréstimo registado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            }
        });
        btnValidarDevolucao.addActionListener(e -> {
            String membro = (String) comboDevMembro.getSelectedItem();
            String livro = (String) comboDevLivro.getSelectedItem();
            if (membro != null && livro != null) {
                Main.getEmprestimos().removeIf(emp -> emp[0].equals(livro) && emp[1].equals(membro));
                modeloTabela.setRowCount(0);
                Main.getEmprestimos().forEach(emp -> modeloTabela.addRow(emp));
                comboLivro.addItem(livro);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione membro e livro.");
            }
        });
        btnProcurar.addActionListener(e -> {
            String filtro = txtFiltro.getText().toLowerCase();
            String membroFiltro = (String) comboFiltroMembro.getSelectedItem();
            modeloTabela.setRowCount(0);
            Main.getEmprestimos().stream()
                .filter(emp -> (filtro.isEmpty() || emp[0].toLowerCase().contains(filtro)) &&
                               (membroFiltro == null || membroFiltro.isEmpty() || emp[1].equals(membroFiltro)))
                .forEach(emp -> modeloTabela.addRow(emp));
        });
        btnExportar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Exportar Empréstimos");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                java.io.File fileToSave = fileChooser.getSelectedFile();
                try (java.io.PrintWriter writer = new java.io.PrintWriter(fileToSave, "UTF-8")) {
                    writer.println("Livro\tMembro\tEstado\tEmpréstimo\tRetorno");
                    for (String[] emp : Main.getEmprestimos()) {
                        writer.println(String.join("\t", emp));
                    }
                    JOptionPane.showMessageDialog(this, "Empréstimos exportados com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao exportar: " + ex.getMessage());
                }
            }
        });
    }
}
