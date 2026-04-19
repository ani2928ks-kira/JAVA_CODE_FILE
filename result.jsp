<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Result</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        .container {
            max-width: 800px;
            margin: 30px auto;
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
        }
        .student-info {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .student-info table {
            width: 100%;
        }
        .student-info td {
            padding: 8px;
        }
        .marks-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .marks-table th, .marks-table td {
            padding: 12px;
            text-align: center;
            border: 1px solid #ddd;
        }
        .marks-table th {
            background: #667eea;
            color: white;
        }
        .marks-table tr:nth-child(even) {
            background: #f8f9fa;
        }
        .result-summary {
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
            text-align: center;
        }
        .pass {
            background: #d4edda;
            border: 2px solid #28a745;
            color: #155724;
        }
        .fail {
            background: #f8d7da;
            border: 2px solid #dc3545;
            color: #721c24;
        }
        .percentage {
            font-size: 36px;
            font-weight: bold;
            margin: 10px 0;
        }
        .btn {
            display: inline-block;
            padding: 12px 24px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            text-decoration: none;
            border-radius: 8px;
            margin: 5px;
            transition: transform 0.3s;
        }
        .btn:hover {
            transform: translateY(-2px);
        }
        .btn-back {
            background: #6c757d;
        }
        .grade {
            font-size: 48px;
            font-weight: bold;
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>📋 Student Result Card</h1>
            <p>Your Result has been Processed</p>
        </div>
        <div class="content">
            
            <%
                // Retrieve attributes from servlet
                String rollno = (String) request.getAttribute("rollno");
                String studentname = (String) request.getAttribute("studentname");
                int sub1 = (Integer) request.getAttribute("sub1");
                int sub2 = (Integer) request.getAttribute("sub2");
                int sub3 = (Integer) request.getAttribute("sub3");
                int sub4 = (Integer) request.getAttribute("sub4");
                int sub5 = (Integer) request.getAttribute("sub5");
                int total = (Integer) request.getAttribute("total");
                double average = (Double) request.getAttribute("average");
                String result = (String) request.getAttribute("result");
                String grade = (String) request.getAttribute("grade");
            %>
            
            <!-- Student Information -->
            <div class="student-info">
                <h3>👤 Student Information</h3>
                <table>
                    <tr>
                        <td width="30%"><strong>Roll Number:</strong></td>
                        <td><%= rollno %></td>
                    </tr>
                    <tr>
                        <td><strong>Student Name:</strong></td>
                        <td><%= studentname %></td>
                    </tr>
                </table>
            </div>
            
            <!-- Marks Table -->
            <h3>📊 Subject Marks</h3>
            <table class="marks-table">
                <tr>
                    <th>Subject</th>
                    <th>Marks Obtained</th>
                    <th>Maximum Marks</th>
                    <th>Status</th>
                </tr>
                <tr>
                    <td>Subject 1</td>
                    <td><%= sub1 %></td>
                    <td>100</td>
                    <td style="color: <%= sub1 >= 40 ? "green" : "red" %>">
                        <%= sub1 >= 40 ? "✓ Pass" : "✗ Fail" %>
                    </td>
                </tr>
                <tr>
                    <td>Subject 2</td>
                    <td><%= sub2 %></td>
                    <td>100</td>
                    <td style="color: <%= sub2 >= 40 ? "green" : "red" %>">
                        <%= sub2 >= 40 ? "✓ Pass" : "✗ Fail" %>
                    </td>
                </tr>
                <tr>
                    <td>Subject 3</td>
                    <td><%= sub3 %></td>
                    <td>100</td>
                    <td style="color: <%= sub3 >= 40 ? "green" : "red" %>">
                        <%= sub3 >= 40 ? "✓ Pass" : "✗ Fail" %>
                    </td>
                </tr>
                <tr>
                    <td>Subject 4</td>
                    <td><%= sub4 %></td>
                    <td>100</td>
                    <td style="color: <%= sub4 >= 40 ? "green" : "red" %>">
                        <%= sub4 >= 40 ? "✓ Pass" : "✗ Fail" %>
                    </td>
                </tr>
                <tr>
                    <td>Subject 5</td>
                    <td><%= sub5 %></td>
                    <td>100</td>
                    <td style="color: <%= sub5 >= 40 ? "green" : "red" %>">
                        <%= sub5 >= 40 ? "✓ Pass" : "✗ Fail" %>
                    </td>
                </tr>
                <tr style="background: #e7f3ff; font-weight: bold;">
                    <td><strong>Total</strong></td>
                    <td><strong><%= total %></strong></td>
                    <td><strong>500</strong></td>
                    <td></td>
                </tr>
            </table>
            
            <!-- Result Summary -->
            <div class="result-summary <%= result.equals("PASS") ? "pass" : "fail" %>">
                <h2>Result: <%= result %></h2>
                <div class="percentage">Average: <%= String.format("%.2f", average) %>%</div>
                <div class="grade">Grade: <%= grade %></div>
                <p>
                    <% if (result.equals("PASS")) { %>
                        🎉 Congratulations! You have passed the examination.
                    <% } else { %>
                        ⚠️ Sorry! You have not passed. Minimum 40% required in each subject.
                    <% } %>
                </p>
            </div>
            
            <!-- Navigation Buttons -->
            <div style="text-align: center; margin-top: 20px;">
                <a href="index.jsp" class="btn btn-back">◀ Back to Form</a>
                <a href="index.jsp" class="btn">📝 Enter New Student</a>
            </div>
            
        </div>
    </div>
</body>
</html>