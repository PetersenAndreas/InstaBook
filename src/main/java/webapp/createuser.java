package webapp;

import Database.DBUsers;
import appLayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@WebServlet(name = "createuser")
public class createuser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");

        User user = new User();

        /*LocalDate d1 = LocalDate.parse(age, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse(new Date().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d2.atStartOfDay(), d1.atStartOfDay());
        long diffDays = diff.toDays();*/

        if (username.isEmpty() || username.length() > 50) {
            request.setAttribute("errorMsg", "Failed to create user. Username must be between 1 - 50 characters");
            //tilføj error for "username already in use"
        }   else if (!user.passwordValidity(password)) {
            request.setAttribute("errorMsg", "Failed to create user. Username must be between 1 - 50 characters");
        }
        /*else if (password.length() < 14 || password.length() > 128) {
            request.setAttribute("errorMsg", "Failed to create user. Password must be between 14 - 128 characters");
            //tilføj error for password uden "special characters"
        }*/   else if (!email.contains("@") || !email.contains(".") || email.length() < 6 || email.length() > 254 ) {
            request.setAttribute("errorMsg", "Failed to create user. email must be valid and between 6 and 254 characters");
            //tilføj potentielt et reelt email-tjek.. om den eksisterer hos forskellige services.
        } /*else if(diffDays < 4748) {
            request.setAttribute("errorMsg", "Failed to create user. You must be at least 13 years old");
        }*/ else {
            DBUsers.createUser(username, password, email, gender, age);
            //request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

        HttpSession session = request.getSession();
        // remove current session
        session.invalidate();
        // generate a new session
        session = request.getSession(true);
        session.setAttribute("username", username);
        //setting session to expire in 15 mins
        session.setMaxInactiveInterval(15*60);

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}