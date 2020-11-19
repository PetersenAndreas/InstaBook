package Database;

import appLayer.Post;

import java.sql.*;
import java.util.ArrayList;

public class DBPosts {

    private static Connection conn;

    public static void createPost (String title, String picturePath, int userid) {
        Post post = new Post(title, picturePath, userid);
        PreparedStatement pStmt = null;
        String sqlCreatePost = "INSERT INTO posts (title, picture, userid) VALUES (?,?,?)";

        try {
            conn = SQLDBConnection.getConnection();
            pStmt = conn.prepareStatement(sqlCreatePost);
            pStmt.setString(1, post.getTitle());
            pStmt.setString(2, post.getPicturePath());
            pStmt.setInt(3, post.getUserid());
            pStmt.execute();

            pStmt.close();
            conn.close();
        } catch (SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(pStmt!=null)
                    pStmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public static ArrayList getPictures () {
        ArrayList<Post> post_List = new ArrayList();
        PreparedStatement pStmt = null;
        String sqlGetPosts = ("SELECT title, picture, userid FROM posts");
        try {
            conn = SQLDBConnection.getConnection();
            pStmt = conn.prepareStatement(sqlGetPosts);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                String SQLTitle = rs.getString("title");
                String SQLPicturePath = rs.getString("picture");
                int SQLUserid = rs.getInt("userid");

                Post newPost = new Post();
                newPost.setTitle(SQLTitle);
                newPost.setPicturePath(SQLPicturePath);
                newPost.setUserid(SQLUserid);
                post_List.add(newPost);
            }
            rs.close();
            pStmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(pStmt!=null)
                    pStmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return post_List;
    }
}