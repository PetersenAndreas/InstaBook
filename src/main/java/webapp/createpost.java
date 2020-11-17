package webapp;

import Database.DBPosts;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

@WebServlet(name = "createpost" )
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class createpost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        Part filePart = request.getPart( "picture");
        InputStream fileName = filePart.getInputStream();

        DBPosts.createPost(title, fileName);
        request.getRequestDispatcher("/WEB-INF/feed.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
    }
}