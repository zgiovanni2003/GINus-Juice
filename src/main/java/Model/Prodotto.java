package Model;

import java.util.Objects;

public class Prodotto {

    private int id;
    private String nome;
    private double prezzo;
    private String descrizione;
    private int quantita; // Nuovo campo per la quantità

    // Costruttore vuoto
    public Prodotto() {}

    // Costruttore con parametri
    public Prodotto(int id, String nome, double prezzo, String descrizione, int quantita) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.quantita = quantita;
    }

    // Getter e Setter per 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter per 'nome'
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter per 'prezzo'
    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    // Getter e Setter per 'descrizione'
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    // Getter e Setter per 'quantita'
    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", prezzo=" + prezzo +
                ", descrizione='" + descrizione + '\'' +
                ", quantita=" + quantita +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return id == prodotto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
