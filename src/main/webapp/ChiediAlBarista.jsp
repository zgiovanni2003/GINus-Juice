<%@ page session="true" %>
<%@ page import="Model.Utente" %>
<%
  Utente utente = (Utente) session.getAttribute("utente");
  String ru = utente != null ? utente.getRuolo() : null;
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

<%@ include file="Fragment/hearder.jsp" %>

<%
  // Verifica se l'utente è loggato e il ruolo è "utente"
  if ("utente".equals(ru)) {
%>
<div class="user-info">
  <p>
    <strong>Scopri cocktail creati su misura per te!</strong><br>
    Il nostro team di esperti mixologist e' pronto a consigliarti drink basati sui tuoi gusti, sulle tue preferenze e sulla tua personalita'. Raccontaci cosa ti piace, e riceverai una selezione di cocktail personalizzati via messaggio. Ogni suggerimento e' pensato per adattarsi al meglio ai tuoi gusti unici!
  </p>
</div>
<% } %>

<div class="user-actions">
  <% if (ru == null) { %>
  <h3 class="login-message">Effettua il login per utilizzare questa funzionalita'</h3>
  <% } else if ("utente".equals(ru)) { %>
  <a href="ScriviDomanda.jsp" class="btn btn-success">Scrivi</a>
  <a href="VisualizzaRisposteServlet" class="btn btn-info">Risposte</a>
  <% } else if ("barista".equals(ru)) { %>
  <a href="VisualizzaDomandeServlet" class="btn btn-warning">Rispondi</a>
  <% } %>
</div>

</body>
</html>
