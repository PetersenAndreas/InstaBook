package webapp;

import Database.CloudinaryDB;
import Database.DBPosts;
import Database.PictureDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;

@WebServlet(name = "createpost" )
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class createpost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        ArrayList<Part> parts = (ArrayList<Part>) request.getParts();

        CloudinaryDB CDB = new CloudinaryDB();
        PictureDB PDB = new PictureDB(CDB);
        ArrayList<String> result_list = PDB.uploadPost(parts);
        request.setAttribute("allTitles", title);
        request.setAttribute("allPicturePaths", result_list);

        DBPosts.createPost(title, result_list.get(result_list.size() -1));

        request.getRequestDispatcher("/WEB-INF/feed.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
    }
}