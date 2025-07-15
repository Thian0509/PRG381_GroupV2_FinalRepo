package mvc.controllers;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import mvc.models.*;

/**
 *
 * @author tshep
 */
@WebServlet(name = "dashboardController", urlPatterns = {"/", "/dashboard"})
public class dashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //validate session
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login"); // Redirect to login if not authenticated
            return;
        }
        
        
        //display
        User user = new User();
        List<User> userList = user.getAllUsers(); 

        // Attach user list to request
        req.setAttribute("userList", userList);

        // Proper forwarding
        RequestDispatcher dispatcher = req.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(req, resp);
    }

}
