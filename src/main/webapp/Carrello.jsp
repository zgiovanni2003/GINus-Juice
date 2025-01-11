<%@ page import="java.util.Map" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="Model.Carrello" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrello - E-commerce</title>
    <link rel="stylesheet" href="styles/styleCarrello.css">
</head>
<body>

<h1>Il tuo Carrello</h1>

<%
    Carrello carrello = (Carrello) session.getAttribute("carrello");

    if (carrello == null || carrello.getProdotti().isEmpty()) {
%>
<p class="empty-cart-message">Il tuo carrello è vuoto.</p>
<a href="Shop.jsp" class="return-link">Torna allo Shop</a>
<%
} else {
    Map<Prodotto, Integer> prodotti = carrello.getProdotti();
    double totaleCarrello = 0;

    for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
        Prodotto prodotto = entry.getKey();
        int quantita = entry.getValue();
        double prezzoTotale = prodotto.getPrezzo() * quantita;
        totaleCarrello += prezzoTotale;
%>
<div class="product-item">
    <!-- Aggiunta immagine del prodotto -->
    <div class="product-image">
        <img src="images/<%= prodotto.getNome()%>.jpg"
             alt="<%= prodotto.getNome() %>">
    </div>
    <p><strong>Prodotto:</strong> <%= prodotto.getNome() %></p>
    <p><strong>Prezzo unitario:</strong> €<%= prodotto.getPrezzo() %></p>
    <p><strong>Quantità:</strong> <%= quantita %></p>
    <p><strong>Prezzo totale:</strong> €<%= prezzoTotale %></p>
    <div class="action-buttons">
        <!-- Aumenta Quantità -->
        <form action="CarrelloServlet" method="post" class="inline-form">
            <input type="hidden" name="id" value="<%= prodotto.getId() %>">
            <input type="hidden" name="action" value="aggiungi">
            <button type="submit" class="button add-button">Aggiungi</button>
        </form>

        <!-- Diminuisci Quantità -->
        <form action="CarrelloServlet" method="post" class="inline-form">
            <input type="hidden" name="id" value="<%= prodotto.getId() %>">
            <input type="hidden" name="action" value="diminuisci">
            <button type="submit" class="button remove-button">Rimuovi</button>
        </form>
    </div>
</div>
<%
    }
%>
<h3 class="cart-total">Totale carrello: €<%= totaleCarrello %></h3>

<form action="MetodoPagamento.jsp" method="get">
    <button type="submit" class="button purchase-button">Acquista</button>
</form>

<form action="SvuotaCarrelloServlet" method="post" class="clear-cart-form">
    <button type="submit" class="button clear-cart-button">Svuota Carrello</button>
</form>

<a href="Shop.jsp" class="return-link">Torna allo Shop</a>
<%
    }
%>

</body>
</html>
