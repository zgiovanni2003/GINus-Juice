package Model;

import java.time.LocalDateTime;

public class Risposta {
    private int id;
    private String messaggio;
    private LocalDateTime dataRicezione;
    private String emailBarista;
    private int idDomanda;

    // Costruttore vuoto
    public Risposta() {}

    // Costruttore con parametri
    public Risposta(int id, String messaggio, LocalDateTime dataRicezione, String emailBarista, int idDomanda) {
        this.id = id;
        this.messaggio = messaggio;
        this.dataRicezione = dataRicezione;
        this.emailBarista = emailBarista;
        this.idDomanda = idDomanda;
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

    public String getEmailBarista() {
        return emailBarista;
    }

    public void setEmailBarista(String emailBarista) {
        this.emailBarista = emailBarista;
    }

    public int getIdDomanda() {
        return idDomanda;
    }

    public void setIdDomanda(int idDomanda) {
        this.idDomanda = idDomanda;
    }

    @Override
    public String toString() {
        return "Risposta{" +
                "id=" + id +
                ", messaggio='" + messaggio + '\'' +
                ", dataRicezione=" + dataRicezione +
                ", emailBarista='" + emailBarista + '\'' +
                ", idDomanda=" + idDomanda +
                '}';
    }
}
