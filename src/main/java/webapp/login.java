package webapp;

import Database.DBConnection;
import appLayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //user login
        ArrayList<User> userList = new ArrayList<User>();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        userList = DBConnection.DBlogin(username, password);
        if (userList.isEmpty()) {
            request.setAttribute("errorMsg", "Failed to login. Please try again");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else {
            request.setAttribute("username", userList.get(0).getUsername());
            request.getRequestDispatcher("/feed.jsp").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}