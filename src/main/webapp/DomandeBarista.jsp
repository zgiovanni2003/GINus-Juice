<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, Model.Domanda" %>
<%
    // Recupero delle domande dalla richiesta
    List<Domanda> domande = (List<Domanda>) request.getAttribute("domande");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Domande da Rispondere</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #333;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .form-group {
            margin-bottom: 10px;
        }
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .alert {
            background-color: #ffcc00;
            color: #333;
            padding: 10px;
            border-radius: 4px;
            margin: 20px 0;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Domande da Rispondere</h1>

    <% if (domande != null && !domande.isEmpty()) { %>
    <table>
        <thead>
        <tr>
            <th>Domanda</th>
            <th>Email Utente</th>
            <th>Risposta</th>
        </tr>
        </thead>
        <tbody>
        <% for (Domanda domanda : domande) { %>
        <tr>
            <td><%= domanda.getMessaggio() %></td>
            <td><%= domanda.getEmailUtente() %></td>
            <td>
                <form action="RispondiDomandaServlet" method="post">
                    <div class="form-group">
                        <textarea name="risposta" rows="3" placeholder="Scrivi la tua risposta..." required></textarea>
                    </div>
                    <input type="hidden" name="id_domanda" value="<%= domanda.getId() %>">
                    <button type="submit">Invia Risposta</button>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <div class="alert">
        Non ci sono domande in sospeso.
    </div>
    <% } %>
</div>
</body>
</html>
