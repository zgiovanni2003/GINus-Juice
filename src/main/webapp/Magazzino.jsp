<%@ page import="java.util.List" %>
<%@ page import="Model.Prodotto" %>
<%@ page import="Model.Utente" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Magazzino</title>
    <link rel="stylesheet" href="styles/styleMagazzino.css">
</head>
<body>
<%@ include file="Fragment/hearder.jsp" %>

<%
    // Recupera l'utente dalla sessione
    Utente ut = (Utente) session.getAttribute("utente");

    // Controlla se l'utente esiste e ha il ruolo richiesto
    if (ut == null || ut.getRuolo() == null || !ut.getRuolo().equals("magazziniere")) {
        response.sendRedirect("AccessoNegato.jsp");
        return;
    }
%>

<h1>Gestione Magazzino</h1>
<div class="table-container">
    <table>
        <thead>
        <tr>
            <th>Immagine</th>
            <th>Nome</th>
            <th>Prezzo</th>
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
            <!-- Immagine del prodotto -->
            <td>
                <img src="images/<%= prodotto.getNome() %>.jpg"
                     alt="<%= prodotto.getNome() %>" class="product-image">
            </td>
            <td><%= prodotto.getNome() %></td>
            <td><%= prodotto.getPrezzo() %></td>
            <td>
                <!-- Form per modifica -->
                <form action="VisualizzaModificaProdottoServlet" method="post" class="inline-form">
                    <input type="hidden" name="id" value="<%= prodotto.getId() %>">
                    <button type="submit" class="btn btn-edit">Modifica</button>
                </form>
                <!-- Form per eliminazione -->
                <form action="EliminaProdottoServlet" method="post" class="inline-form">
                    <input type="hidden" name="id" value="<%= prodotto.getId() %>">
                    <button type="submit" class="btn btn-delete">Elimina</button>
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="4" class="no-products">Nessun prodotto disponibile.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
