package com.cookieservlet;

// Step 1: Required imports
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

// Step 2: Create servlet class and use WebServlet annotation
@WebServlet("/CookieServlet")
public class CookieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Step 3: Handle GET requests
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Check if logout parameter is present
        String logout = request.getParameter("logout");
        
        if (logout != null && logout.equals("true")) {
            // Delete the cookie
            Cookie cookie = new Cookie("user", "");
            cookie.setMaxAge(0); // This deletes the cookie
            cookie.setPath("/");
            response.addCookie(cookie);
            
            // Show logout message
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Logged Out</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 50px; background-color: #f0f0f0; }");
            out.println(".container { max-width: 500px; margin: 0 auto; padding: 30px; background-color: white; border-radius: 10px; text-align: center; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
            out.println(".btn { display: inline-block; padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px; margin-top: 20px; }");
            out.println(".btn:hover { background-color: #45a049; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2 style='color:green'>✓ You have been successfully logged out!</h2>");
            out.println("<a href='CookieServlet' class='btn'>Login Again</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        
        // Regular GET request handling (no logout)
        String userName = request.getParameter("userName");
        
        if (userName != null && !userName.isEmpty()) {
            // Create a new cookie
            Cookie userCookie = new Cookie("user", userName);
            userCookie.setMaxAge(30 * 24 * 60 * 60); // Cookie expires in 30 days
            userCookie.setPath("/");
            response.addCookie(userCookie);
        }
        
        // Read existing cookies
        Cookie[] cookies = request.getCookies();
        String existingUser = null;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user")) {
                    existingUser = cookie.getValue();
                    break;
                }
            }
        }
        
        // Get session count
        HttpSession session = request.getSession();
        Integer count = (Integer) session.getAttribute("count");
        if (count == null) {
            count = 0;
        }
        
        // Generate HTML response
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Cookie Example</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 50px; background-color: #f0f0f0; }");
        out.println(".container { max-width: 500px; margin: 0 auto; padding: 30px; background-color: white; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
        out.println("input[type='text'] { width: 70%; padding: 10px; margin: 10px 0; border: 1px solid #ccc; border-radius: 5px; }");
        out.println("input[type='submit'] { padding: 10px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; }");
        out.println("input[type='submit']:hover { background-color: #45a049; }");
        out.println(".logout-btn { display: inline-block; padding: 10px 20px; background-color: #f44336; color: white; text-decoration: none; border-radius: 5px; margin-top: 20px; }");
        out.println(".logout-btn:hover { background-color: #da190b; }");
        out.println(".info { margin-top: 20px; padding: 10px; background-color: #e7f3ff; border-left: 4px solid #2196F3; font-size: 14px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        
        if (existingUser != null) {
            count++;
            session.setAttribute("count", count);
            out.println("<h2 style='color:blue'>Welcome back, " + existingUser + "!</h2>");
            out.println("<h2 style='color:magenta'>You have visited this page " + count + " times!</h2>");
            out.println("<div class='info'>");
            out.println("<strong>Cookie Information:</strong><br>");
            out.println("• Cookie Name: user<br>");
            out.println("• Cookie Value: " + existingUser + "<br>");
            out.println("• Cookie will expire in 30 days");
            out.println("</div>");
            out.println("<a href='CookieServlet?logout=true' class='logout-btn'>Logout</a>");
        } else {
            out.println("<h2 style='color:red'>Welcome Guest! Please Login</h2>");
            out.println("<form action='CookieServlet' method='post'>");
            out.println("Enter your name: <input type='text' name='userName' required>");
            out.println("<input type='submit' value='Login'>");
            out.println("</form>");
            out.println("<div class='info'>");
            out.println("<strong>Note:</strong> A cookie will be stored to remember you for 30 days.");
            out.println("</div>");
        }
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    // Handle POST requests (for login)
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userName = request.getParameter("userName");
        
        if (userName != null && !userName.trim().isEmpty()) {
            // Create cookie for the user
            Cookie cookie = new Cookie("user", userName.trim());
            cookie.setMaxAge(30 * 24 * 60 * 60); // 30 days
            cookie.setPath("/");
            response.addCookie(cookie);
            
            // Create session for visit count
            HttpSession session = request.getSession();
            session.setAttribute("count", 0);
            
            // Redirect back to the servlet
            response.sendRedirect("CookieServlet");
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Error</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 50px; }");
            out.println(".error { max-width: 500px; margin: 0 auto; padding: 20px; background-color: #ffebee; border: 1px solid #f44336; border-radius: 5px; text-align: center; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='error'>");
            out.println("<h3 style='color:red'>Error: Please enter a valid name!</h3>");
            out.println("<a href='CookieServlet'>Go back</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}