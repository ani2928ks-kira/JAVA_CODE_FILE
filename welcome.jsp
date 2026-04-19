<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome - Session Started</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        .container {
            max-width: 700px;
            margin: 50px auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        .header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }
        .content {
            padding: 30px;
        }
        .message {
            padding: 20px;
            margin: 20px 0;
            border-radius: 10px;
            font-size: 18px;
            text-align: center;
        }
        .success {
            background: #d4edda;
            border-left: 4px solid #28a745;
            color: #155724;
        }
        .info {
            background: #e7f3ff;
            border-left: 4px solid #2196F3;
            color: #004085;
        }
        .warning {
            background: #fff3cd;
            border-left: 4px solid #ffc107;
            color: #856404;
        }
        .session-details {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            margin: 20px 0;
        }
        .session-details table {
            width: 100%;
            border-collapse: collapse;
        }
        .session-details td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .session-details td:first-child {
            font-weight: bold;
            width: 40%;
        }
        .timer {
            font-size: 36px;
            font-weight: bold;
            color: #667eea;
            text-align: center;
            margin: 20px 0;
        }
        .link {
            display: inline-block;
            padding: 12px 24px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            margin: 10px;
            transition: transform 0.3s;
        }
        .link:hover {
            transform: translateY(-2px);
        }
        .btn-danger {
            background: #dc3545;
        }
        .btn-success {
            background: #28a745;
        }
    </style>
    <script>
        var totalSeconds;
        var timer;
        
        function startTimer(minutes) {
            totalSeconds = minutes * 60;
            updateTimerDisplay();
            timer = setInterval(function() {
                if (totalSeconds <= 0) {
                    clearInterval(timer);
                    document.getElementById('timer').innerHTML = '⏰ SESSION EXPIRED!';
                    document.getElementById('timer').style.color = 'red';
                    document.getElementById('expiry-message').innerHTML = 
                        '<div class="warning" style="margin-top: 20px;">⚠️ Your session has expired! <a href="index.jsp">Click here to start a new session</a></div>';
                } else {
                    totalSeconds--;
                    updateTimerDisplay();
                }
            }, 1000);
        }
        
        function updateTimerDisplay() {
            var minutes = Math.floor(totalSeconds / 60);
            var seconds = totalSeconds % 60;
            document.getElementById('timer').innerHTML = 
                minutes + ':' + (seconds < 10 ? '0' + seconds : seconds);
            document.getElementById('timer-seconds').innerHTML = totalSeconds + ' seconds';
        }
    </script>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>🎉 Session Started Successfully!</h1>
        <p>Your session is now active</p>
    </div>
    <div class="content">
        
        <%
            // Get parameters from request
            String userName = request.getParameter("userName");
            String expiryMinutesStr = request.getParameter("expiryMinutes");
            
            int expiryMinutes = 1; // default 1 minute
            
            if (expiryMinutesStr != null && !expiryMinutesStr.isEmpty()) {
                expiryMinutes = Integer.parseInt(expiryMinutesStr);
            }
            
            if (userName == null || userName.trim().isEmpty()) {
                userName = (String) session.getAttribute("userName");
                if (userName == null) {
                    userName = "Guest";
                }
            } else {
                session.setAttribute("userName", userName.trim());
            }
            
            // Set session timeout in seconds (convert minutes to seconds)
            int expirySeconds = expiryMinutes * 60;
            session.setMaxInactiveInterval(expirySeconds);
            
            // Track visit count
            Integer visitCount = (Integer) session.getAttribute("visitCount");
            if (visitCount == null) {
                visitCount = 1;
            } else {
                visitCount++;
            }
            session.setAttribute("visitCount", visitCount);
            
            // Store expiry time in session
            session.setAttribute("expiryMinutes", expiryMinutes);
        %>
        
        <!-- Welcome Message -->
        <div class="message success">
            <strong>👋 Hello, <%= userName %>!</strong><br>
            Welcome to your session management dashboard
        </div>
        
        <!-- Session Details -->
        <div class="session-details">
            <h3>📊 Session Information</h3>
            <table>
                <tr>
                    <td>Session ID:</td>
                    <td><%= session.getId() %></td>
                </tr>
                <tr>
                    <td>User Name:</td>
                    <td><strong><%= userName %></strong></td>
                </tr>
                <tr>
                    <td>Session Created:</td>
                    <td><%= new java.util.Date(session.getCreationTime()) %></td>
                </tr>
                <tr>
                    <td>Last Access Time:</td>
                    <td><%= new java.util.Date(session.getLastAccessedTime()) %></td>
                </tr>
                <tr>
                    <td>Visit Count:</td>
                    <td><%= visitCount %> time(s)</td>
                </tr>
                <tr>
                    <td>Session Timeout:</td>
                    <td><%= expiryMinutes %> minute(s) (<%= expirySeconds %> seconds)</td>
                </tr>
                <tr>
                    <td>Session Will Expire:</td>
                    <td><%= new java.util.Date(session.getCreationTime() + (expirySeconds * 1000)) %></td>
                </tr>
            </table>
        </div>
        
        <!-- Session Expiry Timer -->
        <div class="message info">
            <h3>⏰ Session Expiry Timer</h3>
            <div class="timer" id="timer"></div>
            <p style="text-align: center;">
                <span id="timer-seconds"></span> remaining before session expires
            </p>
            <p><small>Session will expire after <strong><%= expiryMinutes %> minute(s)</strong> of inactivity</small></p>
            <div id="expiry-message"></div>
        </div>
        
        <!-- Action Links -->
        <div style="text-align: center; margin-top: 30px;">
            <a href="checkSession.jsp" class="link">🔍 Check Session Value</a>
            <a href="index.jsp" class="link btn-success">🏠 Start New Session</a>
            <a href="logout.jsp" class="link btn-danger">🚪 Logout</a>
        </div>
        
        <!-- Instructions -->
        <div class="message warning">
            <strong>📝 Instructions:</strong><br>
            • Click "Check Session Value" to verify your session is still active<br>
            • Wait for <%= expiryMinutes %> minute(s) without any action to see session expiry<br>
            • You can also manually logout using the Logout button
        </div>
        
    </div>
</div>

<script>
    // Start the timer with the configured expiry time
    startTimer(<%= expiryMinutes %>);
</script>
</body>
</html>