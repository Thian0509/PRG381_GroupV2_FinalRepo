package mvc.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.models.User;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try
        {
            
            User user = new User();
            var authorizedUser = user.login(email, password);

            if(authorizedUser != null)
            {
                // Sessions
                HttpSession session = req.getSession();  

                session.setAttribute("user", authorizedUser); 
                resp.sendRedirect("dashboard"); 

            }
            else{
                req.setAttribute("errorMessage", "Login failed. Try again.");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);

            } 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            req.setAttribute("errorMessage", "Login failed. Try again.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
