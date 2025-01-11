<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Acquisto" %>
<!DOCTYPE html>
<html>
<head>
    <title>Storico Utente</title>
</head>
<body>
<h1>Storico degli Acquisti</h1>

<%
    // Recupero degli acquisti dal request
    List<Acquisto> acquisti = (List<Acquisto>) request.getAttribute("storico");
    System.out.println(acquisti);
    String emailUtente = request.getParameter("email");
    System.out.println(emailUtente);
%>

<h2>Utente: <%= emailUtente %></h2>

<table border="1">
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
                System.out.println(acquisto);
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

<a href="PannelloAdmin.jsp">Torna al Pannello di Controllo</a>
</body>
</html>
