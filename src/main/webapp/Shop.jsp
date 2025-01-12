<%@ page import="java.util.List" %>
<%@ page import="Model.Prodotto" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shop - E-commerce</title>
    <link rel="stylesheet" href="styles/styleShop.css">
</head>
<body>
<%@ include file="Fragment/hearder.jsp" %>
<h1>Benvenuto nel nostro Shop!</h1>
<h2>Lista dei Prodotti</h2>

<%
    // Recupera i prodotti dal ServletContext
    List<Prodotto> prodotti = (List<Prodotto>) application.getAttribute("prodotti");

    // Verifica se ci sono prodotti disponibili
    if (prodotti != null && !prodotti.isEmpty()) {
%>
<ul class="product-list">
    <% for (Prodotto prodotto : prodotti) { %>
    <li class="product">
        <!-- Mostra immagine del prodotto -->
        <div class="product-image">
            <img src="images/<%= prodotto.getNome()%>.jpg"
                 alt="<%= prodotto.getNome() %>" />
        </div>

        <!-- Mostra nome e prezzo del prodotto -->
        <div class="product-details">
            <strong class="product-name"><%= prodotto.getNome() %></strong><br>
            <span class="product-price">Prezzo: <%= prodotto.getPrezzo() %> €</span><br>
        </div>

        <!-- Bottone per visualizzare i dettagli -->
        <form action="VisualizzaDettagliServlet" method="get">
            <input type="hidden" name="id" value="<%= prodotto.getId() %>">
            <button type="submit" class="button details-button">Visualizza Dettagli</button>
        </form>

        <!-- Bottone per acquistare -->
        <form action="CarrelloServlet" method="post">
            <input type="hidden" name="id" value="<%= prodotto.getId() %>">
            <input type="hidden" name="action" value="aggiungi">
            <button type="submit" class="button buy-button">Compra</button>
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
