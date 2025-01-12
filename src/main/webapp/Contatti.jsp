<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Contatti - GINusJOI</title>
  <link rel="stylesheet" type="text/css" href="styles/styleContatti.css">
  <script src="${pageContext.request.contextPath}/scripts/scriptcont.js" defer></script>
  <style>
    /* Stili inline per il font e lo sfondo */
    body {
      margin: 0;
      padding: 0;
      font-family: 'Arial', sans-serif;
      background-color: #000;
      color: #fff; /* Colore del testo bianco */
    }
  </style>
</head>
<body>
<%@ include file="Fragment/hearder.jsp" %>

<div class="page-container">
  <div class="contact-container">
    <h2>Contatti</h2>

    <div id="contactInfo">
      <p><strong>Telefono:</strong> <a href="tel:+390810994433">081-099-44-33</a></p>
      <p><strong>Cellulare:</strong> <a href="tel:+39333456789">333-456-789</a></p>
      <p><strong>Email:</strong> <a href="mailto:ginusjoice@gmail.com">ginusjoice@gmail.com</a></p>
    </div>

    <div id="map">
      <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3129.863868910545!2d14.4646149!3d40.9245264!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x133bae0793821c51%3A0xb4dc8a844b929970!2sIl%20Re%20Della%20Bistecca!5e0!3m2!1sen!2sus!4v1624022056474!5m2!1sen!2sus" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
    </div>
  </div>
</div>

</body>
</html>


