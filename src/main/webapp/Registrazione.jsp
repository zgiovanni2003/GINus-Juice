<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Registrazione - GINusJOI</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/styleRegistrazione.css">
    <script src="${pageContext.request.contextPath}/scripts/scriptRegistrazione.js" defer></script>
</head>
<body>
<%@ include file="Fragment/herdershop.jsp" %>

<div class="page-container">
    <div class="registration-container">
        <form id="registrationForm" action="RegistrazioneControl" method="post">
            <fieldset>
                <legend>Registrazione</legend>
                <label for="email">Email</label>
                <input id="email" type="text" name="email" placeholder="Inserisci email" required>
                <br>
                <label for="nome">Nome</label>
                <input id="nome" type="text" name="nome" placeholder="Inserisci nome" required>
                <br>
                <label for="cognome">Cognome</label>
                <input id="cognome" type="text" name="cognome" placeholder="Inserisci cognome" required>
                <br>
                <label for="password">Password</label>
                <input id="password" type="password" name="password_hash" placeholder="Inserisci password" required>
                <br>
                <input type="submit" value="Registrati">
            </fieldset>
        </form>
    </div>
</div>

<%@ include file="Fragment/footer.jsp" %>

</body>
</html>
