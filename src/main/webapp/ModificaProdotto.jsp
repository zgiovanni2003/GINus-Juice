<%@ page import="Model.Prodotto" %>
<%@ page import="Model.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Recupera l'utente dalla sessione
    Utente u = (Utente) session.getAttribute("utente");

    // Controlla se l'utente esiste e ha il ruolo richiesto
    if (u == null || u.getRuolo() == null || !u.getRuolo().equals("magazziniere")) {
        response.sendRedirect("AccessoNegato.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Prodotto</title>
    <link rel="stylesheet" href="styles/styleModificaProdotto.css">
</head>
<body>
<div class="container">
    <h1>Modifica Prodotto</h1>
    <%
        Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
        if (prodotto == null) {
    %>
    <p class="error-message">Errore: Prodotto non trovato.</p>
    <%
    } else {
    %>
    <div class="product-details">
        <!-- Visualizza immagine esistente -->
        <div class="product-image">
            <img src="images/<%= prodotto.getNome() %>.jpg?timestamp=<%= System.currentTimeMillis() %>" alt="<%= prodotto.getNome() %>">
        </div>

        <!-- Modulo di modifica -->
        <form action="ModificaProdottoServlet" method="post" enctype="multipart/form-data" class="product-form" onsubmit="return validateForm()">
            <input type="hidden" name="id" value="<%= prodotto.getId() %>">

            <!-- Campo nascosto per il nome dell'immagine esistente -->
            <input type="hidden" name="oldImageName" value="<%= prodotto.getNome() %>.jpg">

            <div class="form-group">
                <label for="nome">Nome:</label>
                <input type="text" id="nome" name="nome" value="<%= prodotto.getNome() %>" required>
            </div>

            <div class="form-group">
                <label for="prezzo">Prezzo (€):</label>
                <input type="number" id="prezzo" name="prezzo" step="0.01" min="0" value="<%= prodotto.getPrezzo() %>" required>
            </div>

            <div class="form-group">
                <label for="descrizione">Descrizione:</label>
                <textarea id="descrizione" name="descrizione" rows="4" required><%= prodotto.getDescrizione() %></textarea>
            </div>

            <div class="form-group">
                <label for="quantita">Quantita:</label>
                <input type="number" id="quantita" name="quantita" min="0" value="<%= prodotto.getQuantita() %>" required>
            </div>

            <div class="form-group">
                <label for="immagine">Nuova immagine del prodotto:</label>
                <input type="file" id="immagine" name="immagine">
            </div>

            <button type="submit" class="btn">Salva Modifiche</button>
        </form>
    </div>
    <%
        }
    %>
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
