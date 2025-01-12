<%@ page import="java.util.List" %>
<%@ page import="Model.Recensione" %>
<%@ page import="Model.Prodotto" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dettagli Prodotto</title>
  <link rel="stylesheet" href="styles/styleDettagliProdotto.css">
</head>
<body>

<div class="product-container">
  <div class="product-image">
    <img src="images/${prodotto.nome}.jpg" alt="${prodotto.nome}">
  </div>
  <div class="product-details">
    <h1>${prodotto.nome}</h1>
    <p class="price">Prezzo: ${prodotto.prezzo}</p>
    <p class="description">Descrizione: ${prodotto.descrizione}</p>
    <p class="quantity">Quantita' Disponibile: ${prodotto.quantita}</p> <!-- Aggiunta della quantità -->
    <!-- Pulsante Compra -->
    <form action="CarrelloServlet" method="post">
      <input type="hidden" name="id" value="${prodotto.id}">
      <input type="hidden" name="action" value="aggiungi">
      <button type="submit" class="button buy-button">Compra</button>
    </form>
  </div>
</div>

<div class="reviews-container">
  <h3>Recensioni</h3>
  <%
    List<Recensione> recensioni = (List<Recensione>) request.getAttribute("recensioni");
    if (recensioni != null && !recensioni.isEmpty()) {
  %>
  <ul class="reviews-list">
    <% for (Recensione recensione : recensioni) { %>
    <li class="review-item">
      <strong><%= recensione.getEmailUtente() %></strong>: <%= recensione.getCorpoRecensione() %> <br>
      <span class="review-date">Data: <%= recensione.getDataRecensione()%></span>
    </li>
    <% } %>
  </ul>
  <%
  } else {
  %>
  <p class="no-reviews">Non ci sono recensioni per questo prodotto.</p>
  <%
    }
  %>
</div>

<div class="add-review">
  <h2>Aggiungi una Recensione</h2>
  <form action="AggiungiRecensioneServlet" method="post">
    <input type="hidden" name="idProdotto" value="${prodotto.id}" />
    <label for="recensione">Scrivi la tua recensione:</label><br>
    <textarea name="corpoRecensione" id="recensione" rows="4" cols="50" required></textarea><br>
    <input type="submit" value="Invia Recensione" class="submit-button" />
  </form>
</div>

<a href="Shop.jsp" class="back-to-shop">Torna allo Shop</a>

</body>
</html>
