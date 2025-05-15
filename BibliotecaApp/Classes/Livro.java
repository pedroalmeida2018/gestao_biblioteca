package Classes;

public class Livro {
    private String isbn;
    private String titulo;
    private String autor;
    private String estado;

    public Livro(String isbn, String titulo, String autor, String estado) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = estado;
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
