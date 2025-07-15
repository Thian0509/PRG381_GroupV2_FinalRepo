package mvc.controllers;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import mvc.models.*;

@WebServlet(name = "UserController", urlPatterns = {"/","/user"})
public class UserController extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
    {
        User user = new User();
        List<User> userList = user.getAllUsers() ; //calling db to get list of users  
        
        // Put model object in request scope
        request.setAttribute("userList", userList);
        
        // Forward to view (JSP)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users.jsp");
        dispatcher.forward(request, response);
       
    }
}
