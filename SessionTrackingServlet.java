package com.sessiontracking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionTracker")
public class SessionTrackingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the session object with 1 minute timeout
        HttpSession session = request.getSession(true);
        
        // Set session timeout to 1 minute (60 seconds)
        session.setMaxInactiveInterval(60);
        
        // Get session information
        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime());
        String sessionId = session.getId();
        
        // Get or create visit count
        Integer visitCount = (Integer) session.getAttribute("visitCount");
        if (visitCount == null) {
            visitCount = 1;
        } else {
            visitCount++;
        }
        
        // Store the updated visit count in session
        session.setAttribute("visitCount", visitCount);
        
        // Get user name if exists
        String userName = (String) session.getAttribute("userName");
        
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Generate HTML response
        generateResponse(out, session, sessionId, createTime, lastAccessTime, visitCount, userName);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userName = request.getParameter("userName");
        
        if (userName != null && !userName.trim().isEmpty()) {
            // Get or create session
            HttpSession session = request.getSession(true);
            
            // Set session timeout to 1 minute
            session.setMaxInactiveInterval(60);
            
            // Store username in session
            session.setAttribute("userName", userName.trim());
            
            // Initialize visit count if not exists
            if (session.getAttribute("visitCount") == null) {
                session.setAttribute("visitCount", 1);
            }
        }
        
        // Redirect to GET method to display the page
        response.sendRedirect("SessionTracker");
    }
    
    private void generateResponse(PrintWriter out, HttpSession session, String sessionId,
                                   Date createTime, Date lastAccessTime, int visitCount, String userName) {
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Session Tracking Demo</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', Arial, sans-serif; margin: 0; padding: 20px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; }");
        out.println(".container { max-width: 600px; margin: 50px auto; background: white; border-radius: 15px; box-shadow: 0 20px 40px rgba(0,0,0,0.1); overflow: hidden; }");
        out.println(".header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 30px; text-align: center; }");
        out.println(".content { padding: 30px; }");
        out.println(".info-box { background: #f8f9fa; padding: 15px; border-radius: 10px; margin: 15px 0; border-left: 4px solid #667eea; }");
        out.println(".warning { background: #fff3cd; border-left-color: #ffc107; }");
        out.println(".success { background: #d4edda; border-left-color: #28a745; }");
        out.println("input[type='text'] { width: 70%; padding: 10px; margin: 10px 0; border: 1px solid #ddd; border-radius: 5px; }");
        out.println("input[type='submit'] { padding: 10px 20px; background: #667eea; color: white; border: none; border-radius: 5px; cursor: pointer; }");
        out.println("input[type='submit']:hover { background: #5a67d8; }");
        out.println(".timer { font-size: 24px; font-weight: bold; color: #667eea; text-align: center; margin: 20px 0; }");
        out.println(".btn { display: inline-block; padding: 10px 20px; background: #dc3545; color: white; text-decoration: none; border-radius: 5px; margin-top: 10px; }");
        out.println("</style>");
        out.println("<script>");
        out.println("var timeLeft = " + session.getMaxInactiveInterval() + ";");
        out.println("var timer = setInterval(function() {");
        out.println("    if (timeLeft <= 0) {");
        out.println("        clearInterval(timer);");
        out.println("        document.getElementById('timer').innerHTML = 'Session Expired! Please refresh the page.';");
        out.println("        document.getElementById('timer').style.color = 'red';");
        out.println("    } else {");
        out.println("        document.getElementById('timer').innerHTML = timeLeft + ' seconds remaining';");
        out.println("    }");
        out.println("    timeLeft--;");
        out.println("}, 1000);");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<div class='header'>");
        out.println("<h1>📊 Session Management Demo</h1>");
        out.println("<p>Session Timeout: 1 Minute</p>");
        out.println("</div>");
        out.println("<div class='content'>");
        
        // Welcome message
        if (userName != null && !userName.isEmpty()) {
            out.println("<div class='success info-box'>");
            out.println("<h2>👋 Hello, " + userName + "!</h2>");
            out.println("</div>");
        }
        
        // Session Information
        out.println("<div class='info-box'>");
        out.println("<h3>📋 Session Information</h3>");
        out.println("<p><strong>Session ID:</strong> " + sessionId + "</p>");
        out.println("<p><strong>Session Creation Time:</strong> " + createTime + "</p>");
        out.println("<p><strong>Last Access Time:</strong> " + lastAccessTime + "</p>");
        out.println("<p><strong>Visit Count:</strong> " + visitCount + "</p>");
        out.println("<p><strong>Session Timeout:</strong> " + session.getMaxInactiveInterval() + " seconds</p>");
        out.println("</div>");
        
        // Session Expiry Timer
        out.println("<div class='warning info-box'>");
        out.println("<h3>⏰ Session Expiry Timer</h3>");
        out.println("<div class='timer' id='timer'></div>");
        out.println("<p><small>Session will expire after 1 minute of inactivity</small></p>");
        out.println("</div>");
        
        // User input form (if not logged in)
        if (userName == null || userName.isEmpty()) {
            out.println("<div class='info-box'>");
            out.println("<h3>🔐 Login</h3>");
            out.println("<form action='SessionTracker' method='post'>");
            out.println("Enter your name: <input type='text' name='userName' required>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");
            out.println("</div>");
        } else {
            out.println("<div style='text-align: center;'>");
            out.println("<a href='SessionTracker?action=invalidate' class='btn' onclick='return confirm(\"Are you sure you want to end this session?\")'>🚪 End Session</a>");
            out.println("</div>");
        }
        
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}