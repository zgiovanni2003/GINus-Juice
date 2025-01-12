<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="styles/styleindex.css">
    <script src="scripts/scriptIndex.js"></script>
</head>
<body>
<%@ include file="Fragment/hearder.jsp" %>

<% Utente utente = (Utente) session.getAttribute("utente");
    String nome="ospite";
    if(utente!=null)
        nome=utente.getNome();
%>


<!-- Menu di navigazione -->

    <!-- Contenuto della pagina -->
    <main class="content">
        <!-- Sezione "Chi siamo" -->
        <div id="about-us">
            <div class="description">
                <h2>Benvenuto, <%= nome %>!</h2>
                <h1>Chi siamo?</h1>
                <p><h3>Siamo due studenti universitari appassionati di mixologia e buon bere. Abbiamo fondato GINusJOI con l'obiettivo di offrire ai nostri clienti una vasta selezione di gin di alta qualità, accompagnata da consigli e ricette per creare cocktail unici. Il nostro sito di e-commerce è progettato per fornire un'esperienza di shopping semplice e piacevole, dove gli amanti del gin possono esplorare le nostre offerte, ricevere consigli personalizzati e trovare tutto ciò di cui hanno bisogno per godersi un drink perfetto, speriamo di essere idonei.</h3>
            </div>
            <div class="image">
                <img src="images/imm_desc.jpg" alt="Immagine illustrativa">
            </div>
        </div>
    </main>
<br/>


<h1>I nostri migliori prodotti..</h1>
<div id="image-carousel">
    <div class="carousel-container">
        <img src="images/imm1.jpeg" class="active" alt="Image 1">
        <img src="images/imm2.jpeg" alt="Image 2">
        <img src="images/imm3.jpeg" alt="Image 3">
        <img src="images/imm4.jpeg" alt="Image 4">
        <img src="images/imm5.jpeg" alt="Image 5">
        <img src="images/imm6.jpeg" alt="Image 6">
        <img src="images/imm7.jpeg" alt="Image 7">
        <img src="images/imm1.jpeg" alt="Image 1">
        <img src="images/imm2.jpeg" alt="Image 2">
        <img src="images/imm3.jpeg" alt="Image 3">
        <img src="images/imm4.jpeg" alt="Image 4">
        <img src="images/imm5.jpeg" alt="Image 5">
        <img src="images/imm6.jpeg" alt="Image 6">
        <img src="images/imm7.jpeg" alt="Image 7">
    </div>
</div>


<div id="overlay">
    <div id="age-confirmation">
        <font color="#000000"><h2>Hai più di 18 anni?</h2></font>
        <button onclick="checkAge(true)">Sì, sono maggiorenne</button>
        <button onclick="redirectToCocaCola()">No, non sono maggiorenne</button>
    </div>
</div>


</body>
</html>