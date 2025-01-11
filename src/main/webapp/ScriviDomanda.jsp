<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Scrivi al Barista</title>
  <link rel="stylesheet" href="styles/styleScriviDomanda.css">
</head>
<body>

<div class="form-container">
  <h1>Scrivi al Barista</h1>
  <form action="ScriviDomandaServlet" method="post">
    <div class="form-group">
      <label for="messaggio">Scrivi la tua richiesta al barista:</label>
      <textarea name="messaggio" id="messaggio" class="form-control" rows="4" required></textarea>
    </div>
    <button type="submit" class="btn btn-primary">Invia</button>
  </form>
</div>

</body>
</html>
