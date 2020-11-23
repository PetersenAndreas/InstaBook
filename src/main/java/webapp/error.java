package webapp;

import appLayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "error")
public class error extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // remove current session
        User sessionUser = (User) session.getAttribute("username");
        session.invalidate();
        // generate a new session
        session = request.getSession(true);
        session.setAttribute("username", sessionUser);
        //setting session to expire in 15 mins
        session.setMaxInactiveInterval(15*60);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
