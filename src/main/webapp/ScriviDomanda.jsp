<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Scrivi al Barista</title>
  <link rel="stylesheet" href="styles/styleScriviDomanda.css">
  <style>
    /* Stili per il popup */
    .popup {
      display: none; /* Nascondi di default */
      position: fixed;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      background-color: #4CAF50; /* Verde per conferma */
      color: white;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
      text-align: center;
      z-index: 1000;
    }
    .popup.show {
      display: block; /* Mostra il popup */
    }
  </style>
</head>
<body>

<div class="form-container">
  <h1>Scrivi al Barista</h1>
  <form id="contactForm" action="ScriviDomandaServlet" method="post">
    <div class="form-group">
      <label for="messaggio">Scrivi la tua richiesta al barista:</label>
      <textarea name="messaggio" id="messaggio" class="form-control" rows="4" required></textarea>
    </div>
    <button type="submit" class="btn btn-primary">Invia</button>
  </form>
</div>

<!-- Popup di conferma -->
<div id="popup" class="popup">
  Il tuo messaggio e' stato inviato!
  Attendi verrai reindirizzato a breve
</div>

<script>
  // Seleziona il modulo e il popup
  const form = document.getElementById('contactForm');
  const popup = document.getElementById('popup');

  // Aggiungi un listener per l'evento di invio del modulo
  form.addEventListener('submit', function(event) {
    event.preventDefault(); // Previeni l'invio del modulo per il test
    popup.classList.add('show'); // Mostra il popup

    // Nascondi il popup dopo 3 secondi
    setTimeout(() => {
      popup.classList.remove('show');
      form.submit(); // Invia il modulo dopo aver mostrato il popup
    }, 3000);
  });
</script>

</body>
</html>
