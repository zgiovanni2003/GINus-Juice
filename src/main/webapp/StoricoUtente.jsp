<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Acquisto" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Storico Utente</title>
    <link rel="stylesheet" href="styles/styleStorico.css">
</head>
<body>
<div class="container">
    <h1>Storico degli Acquisti</h1>

    <%
        // Recupero degli acquisti dal request
        List<Acquisto> acquisti = (List<Acquisto>) request.getAttribute("storico");
        String emailUtente = request.getParameter("email");
    %>

    <h2>Utente: <%= emailUtente %></h2>

    <table>
        <thead>
        <tr>
            <th>ID Acquisto</th>
            <th>Data Acquisto</th>
            <th>Prezzo Totale</th>
            <th>Prodotti Comprati</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (acquisti != null && !acquisti.isEmpty()) {
                for (Acquisto acquisto : acquisti) {
        %>
        <tr>
            <td><%= acquisto.getId() %></td>
            <td><%= acquisto.getDataAcquisto() %></td>
            <td><%= acquisto.getPrezzo() %> €</td>
            <td><%= acquisto.getProdottiComprati() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4">Nessun acquisto trovato per questo utente.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <a href="PannelloAdmin.jsp" class="btn">Torna al Pannello di Controllo</a>
</div>
</body>
</html>
