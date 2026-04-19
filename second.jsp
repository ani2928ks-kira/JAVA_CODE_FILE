<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Session Value</title>
<style>
    body {
        font-family: 'Segoe UI', Arial, sans-serif;
        margin: 0;
        padding: 20px;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .container {
        max-width: 500px;
        margin: 0 auto;
        background: white;
        padding: 40px;
        border-radius: 15px;
        box-shadow: 0 20px 40px rgba(0,0,0,0.1);
        text-align: center;
    }
    h1 {
        color: #333;
        margin-bottom: 30px;
        border-bottom: 2px solid #667eea;
        padding-bottom: 10px;
    }
    .success {
        background: #d4edda;
        border-left: 4px solid #28a745;
        padding: 20px;
        margin: 20px 0;
        border-radius: 8px;
        color: #155724;
        font-size: 18px;
    }
    .error {
        background: #f8d7da;
        border-left: 4px solid #dc3545;
        padding: 20px;
        margin: 20px 0;
        border-radius: 8px;
        color: #721c24;
        font-size: 18px;
    }
    .info {
        background: #e7f3ff;
        border-left: 4px solid #2196F3;
        padding: 15px;
        margin: 20px 0;
        border-radius: 8px;
        font-size: 14px;
    }
    .btn {
        display: inline-block;
        padding: 10px 20px;
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        text-decoration: none;
        border-radius: 5px;
        margin: 5px;
        transition: transform 0.3s;
    }
    .btn:hover {
        transform: translateY(-2px);
    }
</style>
</head>
<body>
<div class="container">
    <h1>📋 Display Session Value</h1>
    
    <%
        // Retrieve the name from session
        String name = (String) session.getAttribute("user");
        
        if (name == null || name.isEmpty()) {
    %>
        <div class="error">
            ❌ Sorry, the session has ended or expired!
        </div>
        <div class="info">
            <strong>Possible Reasons:</strong><br>
            • You waited more than 1 minute before clicking the link<br>
            • The session was manually invalidated<br>
            • You haven't started a session yet<br>
            • The session expired due to inactivity
        </div>
        <a href="index.html" class="btn">Start New Session</a>
        <a href="first.jsp" class="btn">Go to First Page</a>
        
    <% } else { %>
        <div class="success">
            ✅ Hello, <strong><%= name %></strong>!
        </div>
        <div class="info">
            <strong>Session Information:</strong><br>
            Session ID: <%= session.getId() %><br>
            Session Created: <%= new java.util.Date(session.getCreationTime()) %><br>
            Last Accessed: <%= new java.util.Date(session.getLastAccessedTime()) %><br>
            Session Timeout: <%= session.getMaxInactiveInterval() %> seconds<br>
            Session is still active!
        </div>
        <a href="first.jsp" class="btn">Back to First Page</a>
        <a href="second.jsp" class="btn">Refresh</a>
        <a href="logout.jsp" class="btn">Logout</a>
    <% } %>
    
</div>
</body>
</html>