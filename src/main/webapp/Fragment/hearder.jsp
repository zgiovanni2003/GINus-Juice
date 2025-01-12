<%@ page import="Model.Utente" %>
<link rel="stylesheet" href="styles/styleHeader.css">
<%
    // Recupera l'utente dalla sessione
    Utente u = (Utente) session.getAttribute("utente");
    String ruolo = (u != null) ? u.getRuolo() : null;
%>
<nav>
    <div class="logo">
        <h1><a href="index.jsp">GINusJOI</a></h1>
    </div>
    <ul class="navbar">
        <li><a href="index.jsp">Home</a></li>
        <li><a href="Shop.jsp">Shop</a></li>
        <li><a href="Carrello.jsp">Carrello</a></li>
        <li><a href="Contatti.jsp">Contattaci</a></li>
        <li><a href="ChiediAlBarista.jsp">Chiedi al Barista</a></li>
        <% if (ruolo != null && ruolo.equals("magazziniere")) { %>
        <li><a href="Magazzino.jsp">Magazzino</a></li>
        <li><a href="AggiungiProdotto.jsp">Aggingi prodotto</a></li>
        <% } else if (ruolo != null && ruolo.equals("admin")) { %>
        <li><a href="PannelloAdmin.jsp">Admin</a></li>
        <% } %>
    </ul>
    <div class="user-actions">
        <% if (u == null) { %>
        <a href="Login.jsp" class="btn">Login</a>
        <a href="Registrazione.jsp"> registrati</a>
        <% } else { %>
        <a href="Logout" class="btn">Logout</a>
        <% } %>
    </div>
</nav>
