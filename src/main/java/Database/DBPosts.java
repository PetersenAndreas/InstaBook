package Database;

import appLayer.Post;
import jdk.internal.util.xml.impl.Input;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

public class DBPosts {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3307/InstaBook";

    //  Database credentials
    static final String USER = "dev";
    static final String PASS = "ax2";

    public static void createPost (String title, InputStream file) {
        Post post = new Post(title, file);

        Connection conn = null;
        PreparedStatement pStmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String sqlCreatePost = "INSERT INTO posts (title, picture) VALUES (?,?)";
            pStmt = conn.prepareStatement(sqlCreatePost);
            pStmt.setString(1, post.getTitle());
            pStmt.setBinaryStream(2, post.getFile());
            pStmt.execute();

            //STEP 6: Clean-up environment
            pStmt.close();
            conn.close();

        } catch (SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(pStmt!=null)
                    pStmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public static ArrayList<Image> getPictures () throws FileNotFoundException {

        byte b[];
        Blob blob;
        ArrayList<Image> image_list = new ArrayList();
        Image image;

        Connection conn = null;
        PreparedStatement pStmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            pStmt = conn.prepareStatement("SELECT * FROM posts");
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                blob = rs.getBlob("picture");
                byte[] imageByte = blob.getBytes(1, (int)blob.length());
                InputStream is=new ByteArrayInputStream(imageByte);
                BufferedImage imag = ImageIO.read(is);
                //ImageIO.write(imag);

                //String bytesBase64 = Base64.getEncoder().encodeToString(imageByte);
                image = imag;

//                JLabel picLabel = new JLabel(new ImageIcon(image));
//                JPanel jPanel = new JPanel();
//                jPanel.add(picLabel);
//                JFrame f = new JFrame();
//                f.add(jPanel);
//                f.setVisible(true);

                //ImageIcon icon = new ImageIcon(image);
                image_list.add(image);
            }
            rs.close();
            pStmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(pStmt!=null)
                    pStmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        return image_list;
    }
}