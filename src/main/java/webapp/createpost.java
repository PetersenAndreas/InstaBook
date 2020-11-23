package webapp;

import Database.CloudinaryDB;
import Database.DBPosts;
import Database.PictureDB;
import appLayer.Post;
import appLayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "createpost" )
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class createpost extends HttpServlet {
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

        int userid_result = sessionUser.getUserid();
        System.out.println(userid_result);

        String title = request.getParameter("title");
        ArrayList<Part> parts = (ArrayList<Part>) request.getParts();
        CloudinaryDB CDB = new CloudinaryDB();
        PictureDB PDB = new PictureDB(CDB);
        ArrayList<String> result_list = PDB.uploadPost(parts);

        DBPosts.createPost(title, result_list.get(result_list.size() -1), userid_result);

        ArrayList<Post> post_list = new ArrayList();
        post_list = DBPosts.getPictures();
        request.setAttribute("allPosts", post_list);

        request.getRequestDispatcher("/WEB-INF/feed.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
    }
}