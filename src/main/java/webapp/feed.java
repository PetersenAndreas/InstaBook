package webapp;

import Database.DBPosts;
import appLayer.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@WebServlet(name = "feed")
public class feed extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        ArrayList<Image> image_list = new ArrayList();
//        image_list = DBPosts.getPictures();
//        request.setAttribute("allPosts", image_list.get(0));

        request.getRequestDispatcher("/WEB-INF/feed.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}