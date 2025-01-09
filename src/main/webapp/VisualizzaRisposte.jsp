<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, Model.Risposta" %>
<%
  // Recupero delle risposte dalla richiesta
  List<Risposta> risposte = (List<Risposta>) request.getAttribute("risposte");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Le Tue Risposte</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      background-color: #f4f4f9;
    }
    .container {
      max-width: 800px;
      margin: 0 auto;
      background: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    h1 {
      text-align: center;
      color: #333;
    }
    .risposta {
      margin-bottom: 15px;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
      background-color: #f9f9f9;
    }
    .risposta p {
      margin: 5px 0;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Le Tue Risposte</h1>
  <% if (risposte != null && !risposte.isEmpty()) { %>
  <% for (Risposta risposta : risposte) { %>
  <div class="risposta">
    <p><strong>Risposta:</strong> <%= risposta.getMessaggio() %></p>
    <p><small><em>Risposto da: <%= risposta.getEmailBarista() %></em></small></p>
  </div>
  <% } %>
  <% } else { %>
  <p>Non hai ricevuto alcuna risposta.</p>
  <% } %>
</div>
</body>
</html>
