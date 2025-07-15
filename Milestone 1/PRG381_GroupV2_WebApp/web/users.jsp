
<%@page import="java.util.List"%>
<%@page import="mvc.models.User"%>

<%
    List<User> userList = (List<User>) request.getAttribute("userList");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View All Users</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/styles.css">
        
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
            table {
                border-collapse: collapse;
                width: 50%;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <div class="navbar">
            <a href="http://localhost:8080/PRG381_GroupV2_WebApp/index.jsp">Home</a>
            <a href="http://localhost:8080/PRG381_GroupV2_WebApp/login.jsp">Login</a>
            <a href="http://localhost:8080/PRG381_GroupV2_WebApp/register.jsp">Register</a>
            <a href="http://localhost:8080/PRG381_GroupV2_WebApp/dashboard.jsp">Dashboard</a>
        </div>

        <div class="content">
            <h1>Students Table</h1>
                <table>
            <thead>
                <tr>
                    <th>Student Number</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Email</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (userList != null) {
                        for (User user : userList) {
                %>
                    <tr>
                        <td><%= user.studentNumber %></td>
                        <td><%= user.name %></td>
                        <td><%= user.surname %></td>
                        <td><%= user.email %></td>
                        <td><%= user.phone %></td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">No students available.</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
            
            
        </div>
    </body>
</html>