<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, Model.Risposta" %>
<%
  // Recupero delle risposte dalla richiesta
  List<Risposta> risposte = (List<Risposta>) request.getAttribute("risposte");
%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Le Tue Risposte</title>
  <link rel="stylesheet" href="styles/styleRisposte.css">
</head>
<body>
<div class="container">
  <h1>Le Tue Risposte</h1>
  <% if (risposte != null && !risposte.isEmpty()) { %>
  <% for (Risposta risposta : risposte) { %>
  <div class="risposta">
    <p><strong>Risposta:</strong> <%= risposta.getMessaggio() %></p>
    <p class="details"><small><em>Risposto da: <%= risposta.getEmailBarista() %></em></small></p>
  </div>
  <% } %>
  <% } else { %>
  <p class="no-responses">Non hai ricevuto alcuna risposta.</p>
  <% } %>
</div>

</body>
</html>
