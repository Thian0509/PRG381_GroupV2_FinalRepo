package mvc.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.models.User;
import org.mindrot.jbcrypt.Auth;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String studentNumber = req.getParameter("studentNumber");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        
        String password = req.getParameter("password");
        
        String hashedPassword = Auth.hashPassword(password);
        System.out.println("Password: " + password + "HashedPassword: " + hashedPassword);
        
        User user = new User(studentNumber,name,surname,email,phone,hashedPassword);
        boolean result = user.register(user);
        
        if(result)
        {
            resp.sendRedirect("login");  // redirect to login.jsp
        }
        else{
            req.setAttribute("errorMessage", "Registration failed. Try again.");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);

        }
        
    }
    
    
}
