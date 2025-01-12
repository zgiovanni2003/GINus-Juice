<%@ page import="java.util.Date" %>
<%@ page import="Model.Utente" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Metodo di Pagamento</title>
  <link rel="stylesheet" href="styles/styleMetodiPagamento.css">
</head>
<body>
<%
  // Recupera l'utente dalla sessione
  Utente ut = (Utente) session.getAttribute("utente");

  // Controlla se l'utente esiste e ha il ruolo richiesto
  if (ut == null ) {
    response.sendRedirect("Login.jsp");
    return;
  }
%>

<div class="payment-container">
  <h1>Inserisci i dettagli di pagamento</h1>
  <form action="AcquistoServlet" method="post" class="payment-form">
    <div class="form-group">
      <label for="nomeIntestatario">Nome Intestatario:</label>
      <input type="text" id="nomeIntestatario" name="nomeIntestatario" placeholder="Mario Rossi" required>
    </div>

    <div class="form-group">
      <label for="numeroCarta">Numero Carta:</label>
      <input type="text" id="numeroCarta" name="numeroCarta"  placeholder="1234 5678 9012 3456" required>
    </div>

    <div class="form-group">
      <label for="scadenza">Data Scadenza:</label>
      <input type="month" id="scadenza" name="scadenza" required>
    </div>

    <div class="form-group">
      <label for="cvv">CVV:</label>
      <input type="text" id="cvv" name="cvv"  placeholder="123" required>
    </div>

    <button type="submit" class="btn">Conferma Pagamento</button>
  </form>
</div>

</body>
</html>
