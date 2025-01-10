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
</head>
<body>

<h1>${prodotto.nome}</h1>
<p>Prezzo: €${prodotto.prezzo}</p>
<p>Descrizione: ${prodotto.descrizione}</p>

<h3>Recensioni</h3>

<%
  List<Recensione> recensioni = (List<Recensione>) request.getAttribute("recensioni");

  if (recensioni != null && !recensioni.isEmpty()) {
%>
<ul>
  <% for (Recensione recensione : recensioni) { %>
  <li>
    <strong><%= recensione.getEmailUtente() %></strong>: <%= recensione.getCorpoRecensione() %> <br>
    Data: <%= recensione.getDataRecensione()%>
  </li>
  <% } %>
</ul>
<%
} else {
%>
<p>Non ci sono recensioni per questo prodotto.</p>
<%
  }
%>

<h2>Aggiungi una Recensione</h2>
<form action="AggiungiRecensioneServlet" method="post">
  <input type="hidden" name="idProdotto" value="${prodotto.id}" />
  <label for="recensione">Scrivi la tua recensione:</label><br>
  <textarea name="corpoRecensione" id="recensione" rows="4" cols="50" required></textarea><br>
  <input type="submit" value="Invia Recensione" />
</form>

<a href="Shop.jsp">Torna allo Shop</a>

</body>
</html>
