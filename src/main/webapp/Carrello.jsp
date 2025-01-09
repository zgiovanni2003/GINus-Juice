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
</head>
<body>

<h1>Il tuo Carrello</h1>

<%
    Carrello carrello = (Carrello) session.getAttribute("carrello");

    if (carrello != null) {
        Map<Prodotto, Integer> prodotti = carrello.getProdotti();

%>
<table>
    <tr>
        <th>Prodotto</th>
        <th>Quantita'</th>
        <th>Prezzo Totale</th>
        <th>Azioni</th>
    </tr>
    <% for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
        Prodotto prodotto = entry.getKey();
        int quantita = entry.getValue();
    %>
    <tr>
        <td><%= prodotto.getNome() %></td>
        <td><%= quantita %></td>
        <td><%= prodotto.getPrezzo() * quantita %></td>
        <td>
            <!-- Aumenta Quantità -->
            <form action="CarrelloServlet" method="post" style="display: inline;">
                <input type="hidden" name="id" value="<%= prodotto.getId() %>">
                <input type="hidden" name="action" value="aggiungi">
                <button type="submit">+</button>
            </form>

            <!-- Diminuisci Quantità -->
            <form action="CarrelloServlet" method="post" style="display: inline;">
                <input type="hidden" name="id" value="<%= prodotto.getId() %>">
                <input type="hidden" name="action" value="diminuisci">
                <button type="submit">-</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<%
} else {
%>
<p>Il carrello è vuoto.</p>
<% } %>

<%
    double totaleCarrello = 0;
    if (carrello != null) {
        for (Map.Entry<Prodotto, Integer> entry : carrello.getProdotti().entrySet()) {
            totaleCarrello += entry.getKey().getPrezzo() * entry.getValue();
        }
    }
%>

<h3>Totale: €<%= totaleCarrello %></h3>

<form action="MetodoPagamento.jsp" method="get">
    <button type="submit">Acquista</button>
</form>


<a href="Shop.jsp">Torna allo Shop</a>

</body>
</html>
