package webapp;

import Database.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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

//        LocalDate d1 = LocalDate.parse(age, DateTimeFormatter.ISO_LOCAL_DATE);
//        LocalDate d2 = LocalDate.parse(new Date().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
//        Duration diff = Duration.between(d2.atStartOfDay(), d1.atStartOfDay());
//        long diffDays = diff.toDays();
//        //4748 er cirka 13 år i dage
//        if(diffDays > 4748)
        // vi skal trække nuværende dato fra age så man er minimum 13 år gammel...


        DBConnection.createUser(username, password, email, gender, age);

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