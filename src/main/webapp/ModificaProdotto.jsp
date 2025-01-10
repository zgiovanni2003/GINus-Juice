<%@ page import="Model.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifica Prodotto</title>
</head>
<body>
<h1>Modifica Prodotto</h1>
<%
    Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
    if (prodotto == null) {
%>
<p>Errore: Prodotto non trovato.</p>
<%
} else {
%>
<form action="ModificaProdottoServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<%= prodotto.getId() %>">

    <!-- Campo nascosto per il nome dell'immagine esistente -->
    <input type="hidden" name="oldImageName" value="<%= prodotto.getNome() + ".jpg" %>"> <!-- Modificato per passare il nome dell'immagine esistente -->

    <label for="nome">Nome:</label><br>
    <input type="text" id="nome" name="nome" value="<%= prodotto.getNome() %>" required><br><br>

    <label for="prezzo">Prezzo (€):</label><br>
    <input type="number" id="prezzo" name="prezzo" step="0.01" value="<%= prodotto.getPrezzo() %>" required><br><br>

    <label for="descrizione">Descrizione:</label><br>
    <textarea id="descrizione" name="descrizione" rows="4" cols="50" required><%= prodotto.getDescrizione() %></textarea><br><br>

    <label for="immagine">Immagine del prodotto:</label><br>
    <input type="file" id="immagine" name="immagine"><br><br>

    <button type="submit">Salva Modifiche</button>
</form>
<%
    }
%>
</body>
</html>
