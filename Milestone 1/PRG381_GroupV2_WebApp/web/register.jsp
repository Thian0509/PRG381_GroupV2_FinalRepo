<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/styles.css">
    </head>
    <body>
        <div class="navbar">
            <a href="http://localhost:8080/PRG381_Group_V2_WebApp/login">Login</a>
            <a href="http://localhost:8080/PRG381_Group_V2_WebApp/dashboard">Dashboard</a>
        </div>

        <div class="content">
            <h1>Registration Form</h1
            
            <c:if test="${not empty errorMessage}">
                <div class="error">${errorMessage}</div>
            </c:if>

            <form action="register" method="post">
                Student Number: <input type="text" name="studentNumber" required/>
                <br><br>
                Name: <input type="text" name="name" required />
                <br><br>
                Surname: <input type="text" name="surname" required/>
                <br><br>
                Email: <input type="email" name="email" required/>
                <br><br>
                Phone: <input type="text" name="phone" required />
                <br><br>
                Password: <input type="password" name="password" required/>
                <br><br>
                <button type="submit">Register</button>
            </form>
        </div>
    </body>
</html>
