package Classes;

import java.time.LocalDate;

public class Emprestimo {
    private Membro membro;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String estado;

    public Emprestimo(Membro membro, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucao, String estado) {
        this.membro = membro;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.estado = estado;
    }

    public Membro getMembro() { return membro; }
    public void setMembro(Membro membro) { this.membro = membro; }
    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
