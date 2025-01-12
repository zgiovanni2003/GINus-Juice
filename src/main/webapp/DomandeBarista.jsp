<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, Model.Domanda" %>
<%
    // Recupero delle domande dalla richiesta
    List<Domanda> domande = (List<Domanda>) request.getAttribute("domande");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Domande da Rispondere</title>
    <link rel="stylesheet" href="styles/styleDomande.css">
</head>
<body>
<div class="container">
    <h1>Domande da Rispondere</h1>

    <% if (domande != null && !domande.isEmpty()) { %>
    <table>
        <thead>
        <tr>
            <th>Domanda</th>
            <th>Email Utente</th>
            <th>Risposta</th>
        </tr>
        </thead>
        <tbody>
        <% for (Domanda domanda : domande) { %>
        <tr>
            <td><%= domanda.getMessaggio() %></td>
            <td><%= domanda.getEmailUtente() %></td>
            <td>
                <form action="RispondiDomandaServlet" method="post">
                    <div class="form-group">
                        <textarea name="risposta" rows="3" placeholder="Scrivi la tua risposta..." required></textarea>
                    </div>
                    <input type="hidden" name="id_domanda" value="<%= domanda.getId() %>">
                    <button type="submit">Invia Risposta</button>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <div class="alert">
        Non ci sono domande in sospeso.
    </div>
    <% } %>
</div>
</body>
</html>
