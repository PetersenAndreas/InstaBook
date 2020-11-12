package webapp;

import Database.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "createuser")
public class createuser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");

        //if (username.isEmpty() || username.length() >50)
        //if (password.length() < 14 && password.length() > 128)
        //if (email.contains("@") && email.contains(".") && email.length() < 6 && email.length() > 254 )
        LocalDateTime now = LocalDateTime.now();
        // vi skal trække nuværende dato fra age så man er minimum 13 år gammel...


        DBConnection.createUser(username, password, email, gender, age);

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}