package Model;



public class Utente {

    private String email;
    private String nome;
    private String cognome;
    private String password;
    private boolean stato;
    private String ruolo;

    // Costruttori
    public Utente() {}

    public Utente( String email, String nome, String cognome, String password, boolean stato, String ruolo) {

        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.stato = stato;
        this.ruolo = ruolo;
    }

    // Getter e Setter




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}


