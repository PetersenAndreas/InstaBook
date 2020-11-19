package webapp;

import Database.CloudinaryDB;
import Database.DBPosts;
import Database.DBUsers;
import Database.PictureDB;
import appLayer.Post;
import appLayer.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;

@WebServlet(name = "createpost" )
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class createpost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //String username = request.getParameter("username");
        //String password = request.getParameter("password");
        ArrayList<User> userList;
        userList = DBUsers.getAllUsers();
        //HttpSession session = request.getSession();
        //session = request.getSession(true);
        //String username = session.getAttribute("username")
        int userid_result = userList.get(2).getUserid();
        System.out.println(userid_result);

        String title = request.getParameter("title");
        ArrayList<Part> parts = (ArrayList<Part>) request.getParts();
        CloudinaryDB CDB = new CloudinaryDB();
        PictureDB PDB = new PictureDB(CDB);
        ArrayList<String> result_list = PDB.uploadPost(parts);

        //request.setAttribute("allTitles", title);
        //request.setAttribute("allPicturePaths", result_list);
        //request.setAttribute("userID", title);

        DBPosts.createPost(title, result_list.get(result_list.size() -1), userid_result);

        ArrayList<Post> post_list = new ArrayList();
        post_list = DBPosts.getPictures();
        request.setAttribute("allPosts", post_list);

        request.getRequestDispatcher("/WEB-INF/feed.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
    }
}