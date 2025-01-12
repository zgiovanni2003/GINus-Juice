<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Login - GINusJOI</title>
    <link rel="stylesheet" type="text/css" href="styles/styleLogin.css">
    <script src="${pageContext.request.contextPath}/scripts/scriptLogin.js" defer></script>
</head>
<body>


<div class="page-container">
    <div class="login-container">
        <form id="loginForm" action="CheckLogin" method="post">
            <fieldset>
                <legend>Login</legend>
                <label for="email">Email</label>
                <input id="email" type="text" name="email" placeholder="Inserisci email" required>
                <br>
                <label for="password">Password</label>
                <input id="password" type="password" name="password" placeholder="Inserisci password" required>
                <br>
                <input type="submit" value="Accedi">
            </fieldset>
        </form>
        <a href="Registrazione.jsp">registrati</a>
    </div>
</div>




</body>
</html>
