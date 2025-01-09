package Model;

import java.time.LocalDateTime;

public class Domanda {
    private int id;
    private String messaggio;
    private LocalDateTime dataRicezione;
    private boolean stato;
    private String emailUtente;

    // Costruttore vuoto
    public Domanda() {}

    // Costruttore con parametri
    public Domanda(int id, String messaggio, LocalDateTime dataRicezione, boolean stato, String emailUtente) {
        this.id = id;
        this.messaggio = messaggio;
        this.dataRicezione = dataRicezione;
        this.stato = stato;
        this.emailUtente = emailUtente;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public LocalDateTime getDataRicezione() {
        return dataRicezione;
    }

    public void setDataRicezione(LocalDateTime dataRicezione) {
        this.dataRicezione = dataRicezione;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    @Override
    public String toString() {
        return "Domanda{" +
                "id=" + id +
                ", messaggio='" + messaggio + '\'' +
                ", dataRicezione=" + dataRicezione +
                ", stato=" + stato +
                ", emailUtente='" + emailUtente + '\'' +
                '}';
    }
}
