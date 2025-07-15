<%@page import="java.util.List"%>
<%@page import="mvc.models.User"%>

<%
    List<User> userList = (List<User>) request.getAttribute("userList");
%>

<%
//Current User
    User currentUser = (User) session.getAttribute("user");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/styles.css">
        
        <style>
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
            <a href="http://localhost:8080/PRG381_Group_V2_WebApp/logout">Logout</a>
            <a href="http://localhost:8080/PRG381_Group_V2_WebApp/register">Register</a>
        </div>
        <h1><p>Logged User: <%= currentUser.name %> <%= currentUser.surname%></h1>
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
