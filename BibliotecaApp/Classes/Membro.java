package Classes;

public class Membro {
    private String numeroSocio;
    private String primeiroNome;
    private String apelido;
    private String email;

    public Membro(String numeroSocio, String primeiroNome, String apelido, String email) {
        this.numeroSocio = numeroSocio;
        this.primeiroNome = primeiroNome;
        this.apelido = apelido;
        this.email = email;
    }

    public String getNumeroSocio() { return numeroSocio; }
    public void setNumeroSocio(String numeroSocio) { this.numeroSocio = numeroSocio; }
    public String getPrimeiroNome() { return primeiroNome; }
    public void setPrimeiroNome(String primeiroNome) { this.primeiroNome = primeiroNome; }
    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
