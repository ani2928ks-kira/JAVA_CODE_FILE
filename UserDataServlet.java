package com.HTTP_REQ_RES;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/processUser")
public class UserDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get parameters from the request
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String designation = request.getParameter("designation");
        
        // Perform basic validation
        String message;
        
        if (username == null || username.trim().isEmpty()) {
            message = "Error: Username is required";
            request.setAttribute("message", message);
            request.setAttribute("error", true);
        } else if (email == null || email.trim().isEmpty()) {
            message = "Error: Email is required";
            request.setAttribute("message", message);
            request.setAttribute("error", true);
        } else if (designation == null || designation.trim().isEmpty()) {
            message = "Error: Designation is required";
            request.setAttribute("message", message);
            request.setAttribute("error", true);
        } else {
            // Process the data (in real app, you might save to database)
            message = "Success: User data processed successfully!";
            request.setAttribute("message", message);
            request.setAttribute("error", false);
            
            // Store data in request attributes to be accessed by JSP
            request.setAttribute("username", username.trim());
            request.setAttribute("email", email.trim());
            request.setAttribute("designation", designation.trim());
        }
        
        // Forward to result JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/result.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to the input form
        response.sendRedirect("index.jsp");
    }
}