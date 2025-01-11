<%@ page session="true" %>
<%@ page import="Model.Utente" %>
<%
  Utente utente = (Utente) session.getAttribute("utente");
  String ruolo = utente != null ? utente.getRuolo() : null;
%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Area Utente</title>
  <link rel="stylesheet" href="styles/styleChiediAlBarista.css">
</head>
<body>

<div class="user-actions">
  <% if (ruolo == null) { %>
  <h3 class="login-message">Effettua il login per utilizzare questa funzionalità</h3>
  <% } else if ("utente".equals(ruolo)) { %>
  <a href="ScriviDomanda.jsp" class="btn btn-success">Scrivi</a>
  <a href="VisualizzaRisposteServlet" class="btn btn-info">Risposte</a>
  <% } else if ("barista".equals(ruolo)) { %>
  <a href="VisualizzaDomandeServlet" class="btn btn-warning">Rispondi</a>
  <% } %>
</div>

</body>
</html>
