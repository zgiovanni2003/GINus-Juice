package  Model;

import Model.Risposta;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RispostaTest {

    @Test
    void testCostruttoreConParametri() {
        // Setup
        int id = 1;
        String messaggio = "Risposta di test";
        LocalDateTime dataRicezione = LocalDateTime.of(2025, 1, 1, 12, 0);
        String emailBarista = "barista@test.com";
        int idDomanda = 100;

        // Esegui
        Risposta risposta = new Risposta(id, messaggio, dataRicezione, emailBarista, idDomanda);

        // Verifica
        assertEquals(id, risposta.getId());
        assertEquals(messaggio, risposta.getMessaggio());
        assertEquals(dataRicezione, risposta.getDataRicezione());
        assertEquals(emailBarista, risposta.getEmailBarista());
        assertEquals(idDomanda, risposta.getIdDomanda());
    }

    @Test
    void testGetterESetter() {
        // Setup
        Risposta risposta = new Risposta();

        int id = 2;
        String messaggio = "Nuova risposta";
        LocalDateTime dataRicezione = LocalDateTime.now();
        String emailBarista = "altrobarista@test.com";
        int idDomanda = 101;

        // Esegui
        risposta.setId(id);
        risposta.setMessaggio(messaggio);
        risposta.setDataRicezione(dataRicezione);
        risposta.setEmailBarista(emailBarista);
        risposta.setIdDomanda(idDomanda);

        // Verifica
        assertEquals(id, risposta.getId());
        assertEquals(messaggio, risposta.getMessaggio());
        assertEquals(dataRicezione, risposta.getDataRicezione());
        assertEquals(emailBarista, risposta.getEmailBarista());
        assertEquals(idDomanda, risposta.getIdDomanda());
    }

    @Test
    void testToString() {
        // Setup
        int id = 3;
        String messaggio = "Risposta dettagliata";
        LocalDateTime dataRicezione = LocalDateTime.of(2025, 2, 1, 15, 30);
        String emailBarista = "example@barista.com";
        int idDomanda = 200;

        Risposta risposta = new Risposta(id, messaggio, dataRicezione, emailBarista, idDomanda);

        // Esegui
        String toStringResult = risposta.toString();

        // Verifica
        String expected = "Risposta{" +
                "id=" + id +
                ", messaggio='" + messaggio + '\'' +
                ", dataRicezione=" + dataRicezione +
                ", emailBarista='" + emailBarista + '\'' +
                ", idDomanda=" + idDomanda +
                '}';
        assertEquals(expected, toStringResult);
    }
}
