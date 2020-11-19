package Database;

import appLayer.User;
import java.util.Date;

import java.sql.*;
import java.util.ArrayList;

public class DBUsers {

    private static Connection conn;

    public static void createUser (String username, String password, String email, String gender, String age) {
        User user = new User(username, password, email, gender, age);
        PreparedStatement pStmt = null;
        String sqlCreateUser = "INSERT INTO users (username, password, email, gender, age) VALUES (?,?,?,?,?)";

        try {
            conn = SQLDBConnection.getConnection();
            pStmt = conn.prepareStatement(sqlCreateUser);
            pStmt.setString(1, user.getUsername());
            pStmt.setString(2, user.getPassword());
            pStmt.setString(3, user.getEmail());
            pStmt.setString(4, user.getGender());
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
            pStmt.setDate(5, sqlDate);
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

    public static ArrayList getUsers (String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        ArrayList<User> user_List = new ArrayList();
        PreparedStatement pStmt = null;
        String sqlGetUsers = ("SELECT userid, username, password, email, gender, age, user_role " +
                "FROM users " +
                "WHERE username =?");
        try{
            conn = SQLDBConnection.getConnection();
            pStmt = conn.prepareStatement(sqlGetUsers);
            pStmt.setString(1, username);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int SQLuserid = rs.getInt("userid");
                String SQLuser = rs.getString("username");
                String SQLpassword = rs.getString("password");
                String SQLemail = rs.getString("email");
                String SQLgender = rs.getString("gender");
                String SQLage = rs.getString("age");
                String SQLrole = rs.getString("user_role");

                User newUser = new User();
                newUser.setUserid(SQLuserid);
                newUser.setUsername(SQLuser);
                newUser.setPassword(SQLpassword);
                newUser.setEmail(SQLemail);
                newUser.setGender(SQLgender);
                newUser.setAge(SQLage);
                newUser.setRole(SQLrole);
                user_List.add(newUser);

                //Verify password for login
                if(!user_List.get(0).verifyPassword(user.getPassword(), newUser.getPassword())) {
                    user_List = new ArrayList<User>();
                }
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
        return user_List;
    }

    public static ArrayList getAllUsers () {
        ArrayList<User> user_List = new ArrayList();
        PreparedStatement pStmt = null;
        String sqlGetUsers = ("SELECT userid, username " +
                "FROM users");
        try{
            conn = SQLDBConnection.getConnection();
            pStmt = conn.prepareStatement(sqlGetUsers);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int SQLuserid = rs.getInt("userid");
                String SQLuser = rs.getString("username");

                User newUser = new User();
                newUser.setUserid(SQLuserid);
                newUser.setUsername(SQLuser);
                user_List.add(newUser);
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
        return user_List;
    }
}