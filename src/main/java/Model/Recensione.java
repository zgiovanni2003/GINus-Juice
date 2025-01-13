package Model;

import java.util.Date;

public class Recensione {

    private String emailUtente;
    private int idProdotto;
    private String corpoRecensione;
    private Date dataRecensione;

    // Costruttore
    public Recensione(String emailUtente, int idProdotto, String corpoRecensione, Date dataRecensione) {
        this.emailUtente = emailUtente;
        this.idProdotto = idProdotto;
        this.corpoRecensione = corpoRecensione;
        this.dataRecensione = dataRecensione;
    }

    // Getter e Setter
    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getCorpoRecensione() {
        return corpoRecensione;
    }

    public void setCorpoRecensione(String corpoRecensione) {
        this.corpoRecensione = corpoRecensione;
    }

    public Date getDataRecensione() {
        return dataRecensione;
    }

    public void setDataRecensione(Date dataRecensione) {
        this.dataRecensione = dataRecensione;
    }

    // Metodo per restituire una rappresentazione della recensione
    @Override
    public String toString() {
        return "Recensione{" +
                "emailUtente='" + emailUtente + '\'' +
                ", idProdotto=" + idProdotto +
                ", corpoRecensione='" + corpoRecensione + '\'' +
                ", dataRecensione=" + dataRecensione +
                '}';
    }
    public Recensione() {
        // Costruttore vuoto richiesto da alcune librerie e framework
    }


}
