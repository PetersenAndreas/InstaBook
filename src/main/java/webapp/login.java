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

import utils.VerifyRecaptcha;

@WebServlet(name = "login")
public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //user login
        ArrayList<User> userList;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // get reCAPTCHA request param
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        System.out.println(gRecaptchaResponse);
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        User user = new User();
        userList = DBConnection.DBlogin(username, password);

        if(!userList.isEmpty() && user.verifyPassword(password, userList.get(0).getPassword()) && verify) {
            request.setAttribute("username", userList.get(0).getUsername());
            request.getRequestDispatcher("/feed.jsp").forward(request, response);
        }else{
            request.setAttribute("errorMsg", "Failed login, please try agian");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}