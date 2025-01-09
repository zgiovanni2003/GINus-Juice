<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Metodo di Pagamento</title>
</head>
<body>

<h1>Inserisci i dettagli di pagamento</h1>
<form action="AcquistoServlet" method="post">
  <label for="nomeIntestatario">Nome Intestatario:</label>
  <input type="text" id="nomeIntestatario" name="nomeIntestatario" required><br><br>

  <label for="numeroCarta">Numero Carta:</label>
  <input type="text" id="numeroCarta" name="numeroCarta" required><br><br>

  <label for="scadenza">Data Scadenza:</label>
  <input type="month" id="scadenza" name="scadenza" required><br><br>

  <label for="cvv">CVV:</label>
  <input type="text" id="cvv" name="cvv" required><br><br>

  <button type="submit">Conferma Pagamento</button>
</form>

</body>
</html>
