package com.factorial;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/factorial")
public class FactorialServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            int number = Integer.parseInt(request.getParameter("number"));
            
            if (number < 0) {
                throw new IllegalArgumentException("Number cannot be negative");
            }
            if (number > 20) {
                throw new IllegalArgumentException("Number too large, maximum allowed is 20");
            }
            
            long factorial = 1;
            for (int i = 1; i <= number; i++) {
                factorial *= i;
            }
            
            out.println("<html>");
            out.println("<head><title>Factorial Result</title></head>");
            out.println("<body>");
            out.println("<h2>Factorial Result</h2>");
            out.println("<p>Number: " + number + "</p>");
            out.println("<p>Factorial: " + factorial + "</p>");
            out.println("<a href='index.html'>Calculate Another</a>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (NumberFormatException e) {
            out.println("<html><body><h2>Error</h2><p>Please enter a valid number</p><a href='index.html'>Try Again</a></body></html>");
        } catch (IllegalArgumentException e) {
            out.println("<html><body><h2>Error</h2><p>" + e.getMessage() + "</p><a href='index.html'>Try Again</a></body></html>");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}