package com.result;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set character encoding
        request.setCharacterEncoding("UTF-8");
        
        // Get parameters from request
        String rollno = request.getParameter("rollno");
        String studentname = request.getParameter("studentname");
        
        // Get marks for all subjects
        int sub1 = 0, sub2 = 0, sub3 = 0, sub4 = 0, sub5 = 0;
        boolean hasError = false;
        String errorMessage = "";
        
        // Server-side validation
        if (rollno == null || rollno.trim().isEmpty()) {
            errorMessage = "Roll Number is required";
            hasError = true;
        } else if (studentname == null || studentname.trim().isEmpty()) {
            errorMessage = "Student Name is required";
            hasError = true;
        } else {
            // Validate each subject mark
            try {
                sub1 = Integer.parseInt(request.getParameter("sub1"));
                sub2 = Integer.parseInt(request.getParameter("sub2"));
                sub3 = Integer.parseInt(request.getParameter("sub3"));
                sub4 = Integer.parseInt(request.getParameter("sub4"));
                sub5 = Integer.parseInt(request.getParameter("sub5"));
                
                if (sub1 < 0 || sub1 > 100 || sub2 < 0 || sub2 > 100 ||
                    sub3 < 0 || sub3 > 100 || sub4 < 0 || sub4 > 100 ||
                    sub5 < 0 || sub5 > 100) {
                    errorMessage = "All marks must be between 0 and 100";
                    hasError = true;
                }
            } catch (NumberFormatException e) {
                errorMessage = "Please enter valid numeric marks";
                hasError = true;
            }
        }
        
        if (hasError) {
            // Forward to error page
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // Calculate total and average
        int total = sub1 + sub2 + sub3 + sub4 + sub5;
        double average = total / 5.0;
        
        // Determine result (Pass if all subjects >= 40)
        boolean isPass = (sub1 >= 40 && sub2 >= 40 && sub3 >= 40 && sub4 >= 40 && sub5 >= 40);
        String result = isPass ? "PASS" : "FAIL";
        
        // Determine grade based on average
        String grade;
        if (average >= 90) {
            grade = "A+";
        } else if (average >= 80) {
            grade = "A";
        } else if (average >= 70) {
            grade = "B+";
        } else if (average >= 60) {
            grade = "B";
        } else if (average >= 50) {
            grade = "C";
        } else if (average >= 40) {
            grade = "D";
        } else {
            grade = "F";
        }
        
        // Set attributes to be forwarded to JSP
        request.setAttribute("rollno", rollno.trim());
        request.setAttribute("studentname", studentname.trim());
        request.setAttribute("sub1", sub1);
        request.setAttribute("sub2", sub2);
        request.setAttribute("sub3", sub3);
        request.setAttribute("sub4", sub4);
        request.setAttribute("sub5", sub5);
        request.setAttribute("total", total);
        request.setAttribute("average", average);
        request.setAttribute("result", result);
        request.setAttribute("grade", grade);
        
        // Forward to result page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/result.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to index page
        response.sendRedirect("index.jsp");
    }
}