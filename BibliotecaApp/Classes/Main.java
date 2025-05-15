package Classes;

import Vistas.JanelaPrincipal;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static JanelaPrincipal janelaPrincipal;

    public static final List<String[]> livros = new ArrayList<>();
    public static final List<String[]> membros = new ArrayList<>();
    public static final List<String[]> emprestimos = new ArrayList<>();

    public static JanelaPrincipal getJanelaPrincipal() {
        return janelaPrincipal;
    }

    public static List<String[]> getEmprestimos() {
        return emprestimos;
    }

    public static void inicializarDados() {
        livros.add(new String[]{"The Hobbit", "J.R.R. Tolkien", "123456789", "Disponível"});
        livros.add(new String[]{"1984", "George Orwell", "987654321", "Disponível"});
        livros.add(new String[]{"Brave New World", "Aldous Huxley", "192837465", "Emprestado"});

        membros.add(new String[]{"Maria Santos", "maria@email.com", "123456789"});
        membros.add(new String[]{"João Silva", "joao@email.com", "987654321"});
        membros.add(new String[]{"Ana Costa", "ana@email.com", "192837465"});
    }

    public static void adicionarMembro(String[] membro) {
        // Adicionar um novo membro sem sobrescrever os existentes
        membros.add(membro);
    }

    public static void adicionarLivro(String[] livro) {
        // Adicionar um novo livro sem sobrescrever os existentes
        livros.add(livro);
    }

    public static void main(String[] args) {
        inicializarDados();
        javax.swing.SwingUtilities.invokeLater(() -> new JanelaPrincipal());
    }
}
