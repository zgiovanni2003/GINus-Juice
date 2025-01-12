<%@ page import="Model.Utente" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aggiungi Prodotto</title>
    <link rel="stylesheet" href="styles/styleAggiungiProdotto.css">
</head>
<body>

<%
    // Recupera l'utente dalla sessione
    Utente ut = (Utente) session.getAttribute("utente");

    // Controlla se l'utente esiste e ha il ruolo richiesto
    if (ut == null || ut.getRuolo() == null || !ut.getRuolo().equals("magazziniere")) {
        response.sendRedirect("AccessoNegato.jsp");
        return;
    }
%>

<div class="container">
    <h1>Aggiungi un Nuovo Prodotto</h1>
    <form action="AggiungiProdottoServlet" method="post" enctype="multipart/form-data" class="form" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" required>
        </div>

        <div class="form-group">
            <label for="descrizione">Descrizione:</label>
            <textarea id="descrizione" name="descrizione" required></textarea>
        </div>

        <div class="form-group">
            <label for="prezzo">Prezzo (€):</label>
            <input type="number" step="0.01" id="prezzo" name="prezzo" min="0" required>
        </div>

        <div class="form-group">
            <label for="quantita">Quantità:</label>
            <input type="number" id="quantita" name="quantita" min="0" required>
        </div>

        <div class="form-group">
            <label for="immagine">Immagine:</label>
            <input type="file" id="immagine" name="immagine" accept="image/*" required>
        </div>

        <button type="submit" class="btn">Aggiungi Prodotto</button>
    </form>
</div>

<script>
    // Controllo lato client per prevenire quantità o prezzo negativi
    function validateForm() {
        const prezzo = document.getElementById('prezzo').value;
        const quantita = document.getElementById('quantita').value;

        if (prezzo < 0) {
            alert("Il prezzo non può essere negativo.");
            return false;
        }

        if (quantita < 0) {
            alert("La quantità non può essere negativa.");
            return false;
        }

        return true;
    }
</script>
</body>
</html>
