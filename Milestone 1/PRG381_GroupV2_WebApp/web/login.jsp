<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/styles.css">
    </head>
        <style>
            /* Body Styles */
            *{
                box-sizing: border-box;
            }
            body {
                margin: 0;
                font-family: Arial, sans-serif;
            }

            /* Navbar styles */
            .navbar {
                background-color: #333;
                overflow: hidden;
            }

            .navbar a {
                float: left;
                display: block;
                color: white;
                text-align: center;
                padding: 14px 20px;
                text-decoration: none;
            }


            .navbar a:hover {
                background-color: #575757;
            }


            .navbar a.right {
                float: right;
            }


            .content {
                padding: 20px;
            }

            /* Content styles*/

            .content{
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;

                margin: 0;
            }
        </style>
    <body>
        <div class="navbar">
            <a href="http://localhost:8080/PRG381_GroupV2_WebApp/index.jsp">Home</a>
            <a href="http://localhost:8080/PRG381_GroupV2_WebApp/register.jsp">Register</a>
            <a href="http://localhost:8080/PRG381_GroupV2_WebApp/dashboard.jsp">Dashboard</a>
            <a href="http://localhost:8080/PRG381_GroupV2_WebApp/user">Users</a>
        </div>

        <div class="content">
            <h1>Login Form</h1>
        </div>
    </body>
</html>
