<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Check Session Value</title>
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
            max-width: 550px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        .header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 25px;
            text-align: center;
        }
        .content {
            padding: 30px;
            text-align: center;
        }
        .success {
            background: #d4edda;
            border-left: 4px solid #28a745;
            padding: 20px;
            border-radius: 10px;
            margin: 20px 0;
        }
        .error {
            background: #f8d7da;
            border-left: 4px solid #dc3545;
            padding: 20px;
            border-radius: 10px;
            margin: 20px 0;
        }
        .info {
            background: #e7f3ff;
            padding: 15px;
            border-radius: 10px;
            margin: 20px 0;
            text-align: left;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background: #667eea;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin: 5px;
        }
        .btn:hover {
            background: #5a67d8;
        }
        h2 {
            color: #333;
            margin-top: 0;
        }
        .value {
            font-size: 28px;
            font-weight: bold;
            color: #28a745;
            margin: 10px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>🔍 Session Value Check</h1>
    </div>
    <div class="content">
        
        <%
            // Retrieve values from session
            String userName = (String) session.getAttribute("userName");
            Integer expiryMinutes = (Integer) session.getAttribute("expiryMinutes");
            Integer visitCount = (Integer) session.getAttribute("visitCount");
            
            if (userName != null && !userName.isEmpty()) {
        %>
            <div class="success">
                <h2>✅ Session is ACTIVE!</h2>
                <div class="value">Hello, <%= userName %>!</div>
                <p>Your session is still valid.</p>
            </div>
            
            <div class="info">
                <strong>📊 Session Details:</strong><br>
                Session ID: <%= session.getId() %><br>
                User Name: <%= userName %><br>
                Visit Count: <%= visitCount %> time(s)<br>
                Session Created: <%= new java.util.Date(session.getCreationTime()) %><br>
                Last Accessed: <%= new java.util.Date(session.getLastAccessedTime()) %><br>
                Session Timeout: <%= session.getMaxInactiveInterval() %> seconds 
                (<%= session.getMaxInactiveInterval() / 60 %> minutes)<br>
                <strong style="color: #28a745;">✓ Session is still active!</strong>
            </div>
            
        <% } else { %>
            <div class="error">
                <h2>❌ Session has EXPIRED!</h2>
                <p>Sorry, the session has ended or expired.</p>
            </div>
            
            <div class="info">
                <strong>⚠️ Possible Reasons:</strong><br>
                • You waited longer than the configured session timeout<br>
                • The session was manually invalidated<br>
                • You haven't started a session yet<br>
                • Browser cookies were cleared
            </div>
        <% } %>
        
        <div style="margin-top: 20px;">
            <a href="welcome.jsp" class="btn">◀ Back to Dashboard</a>
            <a href="index.jsp" class="btn">🔄 Start New Session</a>
        </div>
        
    </div>
</div>
</body>
</html>