<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Result Management System</title>
    <style>
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }
        .container {
            max-width: 600px;
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
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: bold;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 8px;
            font-size: 14px;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }
        input:focus {
            border-color: #667eea;
            outline: none;
        }
        .error-message {
            color: red;
            font-size: 12px;
            margin-top: 5px;
            display: none;
        }
        .error-border {
            border-color: red !important;
        }
        .success-border {
            border-color: green !important;
        }
        .subject-group {
            background: #f8f9fa;
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .subject-title {
            font-weight: bold;
            margin-bottom: 10px;
            color: #667eea;
        }
        button {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            width: 100%;
            transition: transform 0.3s;
        }
        button:hover {
            transform: translateY(-2px);
        }
        .required {
            color: red;
        }
        .info {
            margin-top: 20px;
            padding: 15px;
            background-color: #e7f3ff;
            border-left: 4px solid #2196F3;
            font-size: 14px;
        }
    </style>
    <script>
        function validateForm() {
            let isValid = true;
            
            // Validate Roll No
            let rollno = document.getElementById("rollno");
            let rollnoError = document.getElementById("rollnoError");
            if (rollno.value.trim() === "") {
                rollnoError.style.display = "block";
                rollno.classList.add("error-border");
                isValid = false;
            } else {
                rollnoError.style.display = "none";
                rollno.classList.remove("error-border");
                rollno.classList.add("success-border");
            }
            
            // Validate Student Name
            let studentname = document.getElementById("studentname");
            let nameError = document.getElementById("nameError");
            if (studentname.value.trim() === "") {
                nameError.style.display = "block";
                studentname.classList.add("error-border");
                isValid = false;
            } else if (studentname.value.trim().length < 2) {
                nameError.innerHTML = "Name must be at least 2 characters";
                nameError.style.display = "block";
                studentname.classList.add("error-border");
                isValid = false;
            } else {
                nameError.style.display = "none";
                studentname.classList.remove("error-border");
                studentname.classList.add("success-border");
            }
            
            // Validate marks for all subjects
            for (let i = 1; i <= 5; i++) {
                let marks = document.getElementById("sub" + i);
                let marksError = document.getElementById("sub" + i + "Error");
                let value = parseInt(marks.value);
                
                if (marks.value.trim() === "") {
                    marksError.innerHTML = "Marks are required";
                    marksError.style.display = "block";
                    marks.classList.add("error-border");
                    isValid = false;
                } else if (isNaN(value) || value < 0 || value > 100) {
                    marksError.innerHTML = "Marks must be between 0 and 100";
                    marksError.style.display = "block";
                    marks.classList.add("error-border");
                    isValid = false;
                } else {
                    marksError.style.display = "none";
                    marks.classList.remove("error-border");
                    marks.classList.add("success-border");
                }
            }
            
            return isValid;
        }
        
        function clearError(element) {
            element.classList.remove("error-border");
            let errorMsg = document.getElementById(element.id + "Error");
            if (errorMsg) {
                errorMsg.style.display = "none";
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>📊 Student Result Management System</h1>
            <p>Enter Student Details and Marks</p>
        </div>
        <div class="content">
            <form action="ResultServlet" method="post" onsubmit="return validateForm()">
                <div class="form-group">
                    <label>Roll Number <span class="required">*</span></label>
                    <input type="text" id="rollno" name="rollno" placeholder="Enter Roll Number" oninput="clearError(this)">
                    <div id="rollnoError" class="error-message">Roll Number is required</div>
                </div>
                
                <div class="form-group">
                    <label>Student Name <span class="required">*</span></label>
                    <input type="text" id="studentname" name="studentname" placeholder="Enter Student Name" oninput="clearError(this)">
                    <div id="nameError" class="error-message">Student Name is required</div>
                </div>
                
                <div class="subject-group">
                    <div class="subject-title">📚 Subject Marks (0-100)</div>
                    
                    <div class="form-group">
                        <label>Subject 1 <span class="required">*</span></label>
                        <input type="number" id="sub1" name="sub1" min="0" max="100" placeholder="Enter marks" oninput="clearError(this)">
                        <div id="sub1Error" class="error-message">Valid marks between 0-100 required</div>
                    </div>
                    
                    <div class="form-group">
                        <label>Subject 2 <span class="required">*</span></label>
                        <input type="number" id="sub2" name="sub2" min="0" max="100" placeholder="Enter marks" oninput="clearError(this)">
                        <div id="sub2Error" class="error-message">Valid marks between 0-100 required</div>
                    </div>
                    
                    <div class="form-group">
                        <label>Subject 3 <span class="required">*</span></label>
                        <input type="number" id="sub3" name="sub3" min="0" max="100" placeholder="Enter marks" oninput="clearError(this)">
                        <div id="sub3Error" class="error-message">Valid marks between 0-100 required</div>
                    </div>
                    
                    <div class="form-group">
                        <label>Subject 4 <span class="required">*</span></label>
                        <input type="number" id="sub4" name="sub4" min="0" max="100" placeholder="Enter marks" oninput="clearError(this)">
                        <div id="sub4Error" class="error-message">Valid marks between 0-100 required</div>
                    </div>
                    
                    <div class="form-group">
                        <label>Subject 5 <span class="required">*</span></label>
                        <input type="number" id="sub5" name="sub5" min="0" max="100" placeholder="Enter marks" oninput="clearError(this)">
                        <div id="sub5Error" class="error-message">Valid marks between 0-100 required</div>
                    </div>
                </div>
                
                <button type="submit">Submit Result</button>
            </form>
            
            <div class="info">
                <strong>📌 Instructions:</strong>
                <ul style="margin: 5px 0;">
                    <li>All fields are required</li>
                    <li>Marks must be between 0 and 100</li>
                    <li>Passing marks: 40% in each subject</li>
                    <li>Average will be calculated automatically</li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>