<%@ page import="java.util.List" %>
<%@ page import="Model.Prodotto" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Magazzino</title>
</head>
<body>
<h1>Gestione Magazzino</h1>
<table border="1">
    <thead>
    <tr>
        <th>Nome</th>
        <th>Prezzo </th>
        <th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Recupera i prodotti dal contesto dell'applicazione
        List<Prodotto> prodotti = (List<Prodotto>) application.getAttribute("prodotti");
        if (prodotti != null) {
            for (Prodotto prodotto : prodotti) {
    %>
    <tr>
        <td><%= prodotto.getNome() %></td>
        <td><%= prodotto.getPrezzo() %></td>
        <td>
            <!-- Form per modifica -->
            <form action="VisualizzaModificaProdottoServlet" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= prodotto.getId() %>">
                <button type="submit">Modifica</button>
            </form>
            <!-- Form per eliminazione -->
            <form action="EliminaProdottoServlet" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= prodotto.getId() %>">
                <button type="submit">Elimina</button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3">Nessun prodotto disponibile.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
