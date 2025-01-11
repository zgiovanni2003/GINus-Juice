<!DOCTYPE html>
<html>
<head>
    <title>Aggiungi Prodotto</title>
</head>
<body>
<h1>Aggiungi un Nuovo Prodotto</h1>
<form action="AggiungiProdottoServlet" method="post" enctype="multipart/form-data">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="nome" required><br><br>

    <label for="descrizione">Descrizione:</label>
    <textarea id="descrizione" name="descrizione" required></textarea><br><br>

    <label for="prezzo">Prezzo:</label>
    <input type="number" step="0.01" id="prezzo" name="prezzo" required><br><br>

    <label for="quantita">Quantità:</label>
    <input type="number" id="quantita" name="quantita" min="0" required><br><br>

    <label for="immagine">Immagine:</label>
    <input type="file" id="immagine" name="immagine" accept="image/*" required><br><br>

    <button type="submit">Aggiungi Prodotto</button>
</form>
</body>
</html>
