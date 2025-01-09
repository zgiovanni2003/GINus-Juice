<%@ page import="java.util.List" %>
<%@ page import="Model.Prodotto" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shop - E-commerce</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<h1>Benvenuto nel nostro Shop!</h1>
<h2>Lista dei Prodotti</h2>

<%
    // Recupera i prodotti dal ServletContext
    List<Prodotto> prodotti = (List<Prodotto>) application.getAttribute("prodotti");

    // Verifica se ci sono prodotti disponibili
    if (prodotti != null && !prodotti.isEmpty()) {
%>
<ul>
    <% for (Prodotto prodotto : prodotti) { %>
    <li>
        <strong><%= prodotto.getNome() %></strong><br>
        Prezzo:  <%= prodotto.getPrezzo() %><br>

        <!-- Bottone per visualizzare i dettagli -->
        <form action="VisualizzaDettagliServlet" method="get">
            <input type="hidden" name="id" value="<%= prodotto.getId() %>">
            <button type="submit">Visualizza Dettagli</button>
        </form>

        <!-- Bottone per acquistare (per ora fittizio) -->
        <form action="compra" method="post">
            <input type="hidden" name="id" value="<%= prodotto.getId() %>">
            <button type="submit">Compra</button>
        </form>
    </li>
    <% } %>
</ul>
<%
} else {
%>
<p>Non ci sono prodotti disponibili al momento.</p>
<% } %>

</body>
</html>
