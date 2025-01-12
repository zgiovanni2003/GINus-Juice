<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Utente" %>
<%@ page import="Model.UtenteDAO" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pannello di Controllo Amministratore</title>
    <link rel="stylesheet" href="styles/stylePannelloAdmin.css">
</head>
<body>
<div class="container">
    <h1>Pannello di Controllo Amministratore</h1>

    <%
        // Recupera l'utente dalla sessione
        Utente u = (Utente) session.getAttribute("utente");

        // Controlla se l'utente esiste e ha il ruolo richiesto
        if (u == null || u.getRuolo() == null || !u.getRuolo().equals("admin")) {
            response.sendRedirect("AccessoNegato.jsp");
            return;
        }
    %>

    <%
        // Recupero lista degli utenti tramite UtenteDAO
        UtenteDAO utenteDAO = new UtenteDAO();
        List<Utente> utenti = utenteDAO.getAllUtenti();
    %>

    <table>
        <thead>
        <tr>
            <th>Email</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Ruolo</th>
            <th>Azioni</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Utente utente : utenti) {
        %>
        <tr>
            <td><%= utente.getEmail() %></td>
            <td><%= utente.getNome() %></td>
            <td><%= utente.getCognome() %></td>
            <td><%= utente.getRuolo() %></td>
            <td class="actions">
                <!-- Azione per rimuovere utente -->
                <form action="RimuoviUtenteServlet" method="post">
                    <input type="hidden" name="email" value="<%= utente.getEmail() %>">
                    <button type="submit" class="btn btn-remove">Rimuovi</button>
                </form>

                <!-- Azione per cambiare ruolo -->
                <form action="AggiornaRuoloServlet" method="post">
                    <input type="hidden" name="email" value="<%= utente.getEmail() %>">
                    <select name="ruolo" class="role-select">
                        <option value="utente" <%= utente.getRuolo().equals("utente") ? "selected" : "" %>>Utente</option>
                        <option value="barista" <%= utente.getRuolo().equals("barista") ? "selected" : "" %>>Barista</option>
                        <option value="magazziniere" <%= utente.getRuolo().equals("magazziniere") ? "selected" : "" %>>Magazziniere</option>
                    </select>
                    <button type="submit" class="btn btn-update">Aggiorna Ruolo</button>
                </form>

                <!-- Azione per visualizzare storico -->
                <form action="StoricoAcquistiServlet" method="get">
                    <input type="hidden" name="email" value="<%= utente.getEmail() %>">
                    <button type="submit" class="btn btn-view">Visualizza Storico</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
