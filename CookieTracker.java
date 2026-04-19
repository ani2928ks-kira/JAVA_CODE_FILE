package com.cookie.demo;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/CookieTracker")
public class CookieTracker extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userName = request.getParameter("userName");
        
        if (userName != null && !userName.trim().isEmpty()) {
            // Create or update user cookie
            Cookie userCookie = new Cookie("userName", URLEncoder.encode(userName.trim(), "UTF-8"));
            userCookie.setMaxAge(30); // Cookie expires in 30 seconds for demo
            userCookie.setPath("/");
            response.addCookie(userCookie);
            
            // Update visit count
            updateVisitCount(request, response, userName);
        } else {
            response.sendRedirect("index.html");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Get all cookies
        Cookie[] cookies = request.getCookies();
        String userName = null;
        int visitCount = 0;
        
        // Extract user info from cookies
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userName")) {
                    userName = URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
                if (cookie.getName().equals("visitCount")) {
                    visitCount = Integer.parseInt(cookie.getValue());
                }
            }
        }
        
        // Generate HTML response
        generateResponse(out, request, userName, visitCount, cookies);
    }
    
    private void updateVisitCount(HttpServletRequest request, HttpServletResponse response, String userName)
            throws IOException {
        
        Cookie[] cookies = request.getCookies();
        int visitCount = 1;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visitCount")) {
                    visitCount = Integer.parseInt(cookie.getValue()) + 1;
                    break;
                }
            }
        }
        
        // Update visit count cookie
        Cookie visitCookie = new Cookie("visitCount", String.valueOf(visitCount));
        visitCookie.setMaxAge(30); // Expires in 30 seconds
        visitCookie.setPath("/");
        response.addCookie(visitCookie);
        
        // Create last visit time cookie
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Cookie timeCookie = new Cookie("lastVisit", URLEncoder.encode(timestamp, "UTF-8"));
        timeCookie.setMaxAge(30);
        timeCookie.setPath("/");
        response.addCookie(timeCookie);
        
        // Redirect to display page
        response.sendRedirect("CookieTracker");
    }
    
    private void generateResponse(PrintWriter out, HttpServletRequest request, 
                                   String userName, int visitCount, Cookie[] cookies) {
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Cookie Tracker - Visit Dashboard</title>");
        out.println("<style>");
        out.println("body { font-family: 'Segoe UI', Arial, sans-serif; margin: 0; padding: 20px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); min-height: 100vh; }");
        out.println(".dashboard { max-width: 900px; margin: 0 auto; }");
        out.println(".card { background: white; border-radius: 15px; padding: 25px; margin-bottom: 20px; box-shadow: 0 10px 30px rgba(0,0,0,0.1); }");
        out.println(".welcome-card { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }");
        out.println(".welcome-card h1 { margin: 0; }");
        out.println("h2 { color: #333; border-bottom: 2px solid #667eea; padding-bottom: 10px; }");
        out.println(".cookie-table { width: 100%; border-collapse: collapse; margin-top: 15px; }");
        out.println(".cookie-table th { background: #667eea; color: white; padding: 12px; text-align: left; }");
        out.println(".cookie-table td { padding: 10px; border-bottom: 1px solid #ddd; }");
        out.println(".cookie-table tr:hover { background: #f5f5f5; }");
        out.println(".expiry-note { background: #fff3cd; border-left: 4px solid #ffc107; padding: 15px; margin: 20px 0; border-radius: 5px; }");
        out.println(".btn { display: inline-block; padding: 10px 20px; background: #dc3545; color: white; text-decoration: none; border-radius: 5px; margin-right: 10px; }");
        out.println(".btn:hover { background: #c82333; }");
        out.println(".btn-logout { background: #28a745; }");
        out.println(".btn-logout:hover { background: #218838; }");
        out.println(".count { font-size: 48px; font-weight: bold; color: #667eea; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='dashboard'>");
        
        // Welcome Card
        if (userName != null && !userName.isEmpty()) {
            out.println("<div class='card welcome-card'>");
            out.println("<h1>🎉 Welcome back, " + userName + "!</h1>");
            out.println("<p style='font-size: 18px; margin-top: 10px;'>We're glad to see you again!</p>");
            out.println("</div>");
            
            // Visit Counter Card
            out.println("<div class='card'>");
            out.println("<h2>📊 Your Visit Statistics</h2>");
            out.println("<div style='text-align: center;'>");
            out.println("<div class='count'>" + visitCount + "</div>");
            out.println("<p style='font-size: 18px;'>You have visited this page <strong>" + visitCount + "</strong> times</p>");
            out.println("</div>");
            out.println("</div>");
        } else {
            out.println("<div class='card'>");
            out.println("<h2>⚠️ No Active Session</h2>");
            out.println("<p>Your cookie has expired or you haven't logged in yet.</p>");
            out.println("<a href='index.html' class='btn btn-logout'>Go to Login</a>");
            out.println("</div>");
        }
        
        // Cookies Information Card
        out.println("<div class='card'>");
        out.println("<h2>🍪 Cookie Information</h2>");
        
        if (cookies != null && cookies.length > 0) {
            out.println("<table class='cookie-table'>");
            out.println("<tr><th>Cookie Name</th><th>Cookie Value</th><th>Path</th><th>Max Age (seconds)</th></tr>");
            
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                String path = cookie.getPath() != null ? cookie.getPath() : "/";
                int maxAge = cookie.getMaxAge();
                
                // Decode URL encoded values
                if (name.equals("userName") || name.equals("lastVisit")) {
                    try {
                        value = URLDecoder.decode(value, "UTF-8");
                    } catch (Exception e) {
                        // Keep original value
                    }
                }
                
                out.println("<tr>");
                out.println("<td><strong>" + name + "</strong></td>");
                out.println("<td>" + value + "</td>");
                out.println("<td>" + path + "</td>");
                out.println("<td>" + (maxAge == -1 ? "Session (until browser closes)" : maxAge + " seconds") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } else {
            out.println("<p style='color: #999;'>No cookies found. Please login first.</p>");
        }
        
        out.println("</div>");
        
        // Cookie Expiry Demonstration Card
        out.println("<div class='card'>");
        out.println("<h2>⏰ Cookie Expiry Demonstration</h2>");
        out.println("<div class='expiry-note'>");
        out.println("<strong>📌 Current Cookie Settings:</strong><br>");
        out.println("• Cookies are set to expire in <strong>30 seconds</strong><br>");
        out.println("• After 30 seconds, cookies will be automatically deleted by the browser<br>");
        out.println("• You will need to login again after cookie expiry<br>");
        out.println("• This demonstrates how cookie expiration works in web applications");
        out.println("</div>");
        
        // Show expiry countdown
        if (userName != null && !userName.isEmpty()) {
            out.println("<div style='margin-top: 20px;'>");
            out.println("<p><strong>⏱️ Cookie Expiry Countdown:</strong> <span id='countdown' style='color: red; font-weight: bold;'>30</span> seconds remaining</p>");
            out.println("</div>");
        }
        
        out.println("</div>");
        
        // Action Buttons
        out.println("<div style='text-align: center; margin-top: 20px;'>");
        out.println("<a href='CookieTracker?action=delete' class='btn' onclick='return confirm(\"Are you sure? This will delete all cookies.\")'>🗑️ Delete All Cookies</a>");
        out.println("<a href='index.html' class='btn btn-logout'>🏠 Home</a>");
        out.println("</div>");
        
        out.println("</div>");
        
        // JavaScript for countdown
        if (userName != null && !userName.isEmpty()) {
            out.println("<script>");
            out.println("var timeLeft = 30;");
            out.println("var timer = setInterval(function() {");
            out.println("    if (timeLeft <= 0) {");
            out.println("        clearInterval(timer);");
            out.println("        document.getElementById('countdown').innerHTML = 'EXPIRED!';");
            out.println("        document.getElementById('countdown').style.color = 'red';");
            out.println("    } else {");
            out.println("        document.getElementById('countdown').innerHTML = timeLeft;");
            out.println("    }");
            out.println("    timeLeft--;");
            out.println("}, 1000);");
            out.println("</script>");
        }
        
        out.println("</body>");
        out.println("</html>");
    }
}