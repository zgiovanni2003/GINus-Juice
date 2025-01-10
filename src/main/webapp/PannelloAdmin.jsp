<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Utente" %>
<%@ page import="Model.UtenteDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pannello di Controllo Amministratore</title>
</head>
<body>
<h1>Pannello di Controllo Amministratore</h1>

<%
    // Recupero lista degli utenti tramite UtenteDAO
    UtenteDAO utenteDAO = new UtenteDAO();
    List<Utente> utenti = utenteDAO.getAllUtenti(); // Assicurati che esista il metodo getAllUtenti
%>

<table border="1">
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
        <td>
            <!-- Azione per rimuovere utente -->
            <form action="RimuoviUtenteServlet" method="post" style="display:inline;">
                <input type="hidden" name="email" value="<%= utente.getEmail() %>">
                <button type="submit">Rimuovi</button>
            </form>

            <!-- Azione per cambiare ruolo -->
            <form action="AggiornaRuoloServlet" method="post" style="display:inline;">
                <input type="hidden" name="email" value="<%= utente.getEmail() %>">
                <select name="ruolo">
                    <option value="utente" <%= utente.getRuolo().equals("utente") ? "selected" : "" %>>Utente</option>
                    <option value="barista" <%= utente.getRuolo().equals("barista") ? "selected" : "" %>>Barista</option>
                    <option value="magazziniere" <%= utente.getRuolo().equals("magazziniere") ? "selected" : "" %>>Magazziniere</option>
                </select>
                <button type="submit">Aggiorna Ruolo</button>
            </form>

            <!-- Azione per visualizzare storico -->
            <form action="StoricoAcquistiServlet" method="get" style="display:inline;">
                <input type="hidden" name="email" value="<%= utente.getEmail() %>">
                <button type="submit">Visualizza Storico</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
