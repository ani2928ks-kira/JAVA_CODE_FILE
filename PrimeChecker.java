package com.primenumber;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PrimeChecker")
public class PrimeChecker extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            // Get the number from the form
            String numberParam = request.getParameter("number");
            
            // Check if parameter is null or empty
            if (numberParam == null || numberParam.trim().isEmpty()) {
                displayError(out, "Error Code: 400", "Please enter a number");
                return;
            }
            
            // Parse the number
            int number = Integer.parseInt(numberParam);
            
            // Validate the number
            if (number < 0) {
                displayError(out, "Error Code: 401", "Number cannot be negative! Please enter a positive number.");
                return;
            }
            
            if (number == 0) {
                displayError(out, "Error Code: 402", "Zero (0) is neither prime nor composite! Please enter a number greater than 1.");
                return;
            }
            
            if (number == 1) {
                displayError(out, "Error Code: 403", "One (1) is neither prime nor composite! Please enter a number greater than 1.");
                return;
            }
            
            // Check if the number is prime
            boolean isPrime = checkPrime(number);
            
            // Display the result
            if (isPrime) {
                displaySuccess(out, number, true);
            } else {
                displaySuccess(out, number, false);
            }
            
        } catch (NumberFormatException e) {
            displayError(out, "Error Code: 404", "Invalid input! Please enter a valid integer number.");
        } catch (Exception e) {
            displayError(out, "Error Code: 500", "An unexpected error occurred: " + e.getMessage());
        }
    }
    
    // Method to check if a number is prime
    private boolean checkPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        
        // Check for odd divisors up to square root
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    // Method to display success result
    private void displaySuccess(PrintWriter out, int number, boolean isPrime) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Prime Number Check Result</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 50px; background-color: #f0f0f0; }");
        out.println(".container { max-width: 500px; margin: 0 auto; padding: 30px; background-color: white; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
        out.println(".success { padding: 20px; background-color: #d4edda; border-left: 4px solid #28a745; border-radius: 5px; }");
        out.println(".failure { padding: 20px; background-color: #f8d7da; border-left: 4px solid #dc3545; border-radius: 5px; }");
        out.println("h2 { margin-top: 0; }");
        out.println(".number { font-size: 24px; font-weight: bold; margin: 10px 0; }");
        out.println(".result { font-size: 18px; margin: 10px 0; }");
        out.println(".btn { display: inline-block; background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-top: 20px; }");
        out.println(".btn:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        
        if (isPrime) {
            out.println("<div class='success'>");
            out.println("<h2>✓ Prime Number Check Result</h2>");
            out.println("<div class='number'>Number: " + number + "</div>");
            out.println("<div class='result'><strong>Result:</strong> " + number + " is a PRIME number!</div>");
            out.println("<div class='explanation'>");
            out.println("<strong>Explanation:</strong><br>");
            out.println(number + " is only divisible by 1 and " + number + " itself.");
            out.println("</div>");
            out.println("</div>");
        } else {
            out.println("<div class='failure'>");
            out.println("<h2>✗ Prime Number Check Result</h2>");
            out.println("<div class='number'>Number: " + number + "</div>");
            out.println("<div class='result'><strong>Result:</strong> " + number + " is NOT a prime number!</div>");
            out.println("<div class='explanation'>");
            out.println("<strong>Explanation:</strong><br>");
            out.println(number + " has divisors other than 1 and itself.");
            
            // Find and display a divisor
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    out.println(number + " is divisible by " + i + " and " + (number / i) + ".");
                    break;
                }
            }
            out.println("</div>");
            out.println("</div>");
        }
        
        out.println("<a href='index.html' class='btn'>Check Another Number</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    // Method to display error message
    private void displayError(PrintWriter out, String errorCode, String message) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Error - Prime Number Checker</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 50px; background-color: #f0f0f0; }");
        out.println(".error-container { max-width: 500px; margin: 0 auto; padding: 30px; background-color: white; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
        out.println(".error-box { padding: 20px; background-color: #f8d7da; border-left: 4px solid #dc3545; border-radius: 5px; }");
        out.println("h2 { color: #dc3545; margin-top: 0; }");
        out.println(".error-code { font-family: monospace; font-size: 16px; font-weight: bold; color: #721c24; margin: 10px 0; }");
        out.println(".error-message { font-size: 16px; margin: 10px 0; }");
        out.println(".btn { display: inline-block; background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; margin-top: 20px; }");
        out.println(".btn:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='error-container'>");
        out.println("<div class='error-box'>");
        out.println("<h2>✗ Input Error</h2>");
        out.println("<div class='error-code'>" + errorCode + "</div>");
        out.println("<div class='error-message'>" + message + "</div>");
        out.println("</div>");
        out.println("<a href='index.html' class='btn'>Try Again</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}