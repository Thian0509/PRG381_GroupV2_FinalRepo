<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/styles.css">
    </head>
    <body>
        <div class="navbar">
            <a href="http://localhost:8080/PRG381_Group_V2_WebApp/register">Register</a>
            <a href="http://localhost:8080/PRG381_Group_V2_WebApp/dashboard">Dashboard</a>
        </div>

        <div class="content">
            <h1>Login Form</h1>
            <c:if test="${not empty errorMessage}">
                <div class="error">${errorMessage}</div>
            </c:if>
            <form action="login" method="post">
                Email: <input type="email" name="email" required/>
                <br><br>
                Password: <input type="password" name="password" required/>
                <br><br>
                <button type="submit">Login</button>
            </form>
            
            
        </div>
    </body>
</html>
