package Model;

import java.util.Date;

public class Acquisto {
    private int id;
    private Date dataAcquisto;
    private double prezzo;
    private String prodottiComprati;
    private String emailUtente;

    // Costruttore vuoto
    public Acquisto() {}

    // Costruttore con parametri
    public Acquisto(Date dataAcquisto, double prezzo, String prodottiComprati, String emailUtente) {
        this.dataAcquisto = dataAcquisto;
        this.prezzo = prezzo;
        this.prodottiComprati = prodottiComprati;
        this.emailUtente = emailUtente;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getProdottiComprati() {
        return prodottiComprati;
    }

    public void setProdottiComprati(String prodottiComprati) {
        this.prodottiComprati = prodottiComprati;
    }

    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }
}
