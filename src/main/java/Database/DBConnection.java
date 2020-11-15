package Database;

import java.sql.*;
import java.util.ArrayList;
import appLayer.User;
import java.util.Date;

public class DBConnection {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3307/InstaBook";

    //  Database credentials
    static final String USER = "dev";
    static final String PASS = "ax2";


    public static ArrayList DBlogin(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Connection conn = null;
        //Statement stmt = null;
        PreparedStatement pStmt = null;
        ArrayList<User> userList = new ArrayList<User>();
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            pStmt = conn.prepareStatement("SELECT userid, username, password, email, gender, age " +
                    "FROM users " +
                    "WHERE username =?");
            pStmt.setString(1, username);
            //pStmt.setString(2, password);
            ResultSet rs = pStmt.executeQuery();

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int SQLuserid  = rs.getInt("userid");
                String SQLuser = rs.getString("username");
                String SQLpassword = rs.getString("password");
                String SQLemail = rs.getString("email");
                String SQLgender = rs.getString("gender");
                String SQLage = rs.getString("age");

                User newUser = new User();

                newUser.setUsername(SQLuser);
                newUser.setPassword(SQLpassword);
                newUser.setEmail(SQLemail);
                newUser.setGender(SQLgender);
                newUser.setAge(SQLage);
                userList.add(newUser);

                //Varify password for login
                if(!userList.get(0).verifyPassword(user.getPassword() , newUser.getPassword())) {
                    userList = new ArrayList<User>();
                }

            }
            //STEP 6: Clean-up environment
            rs.close();
            //stmt.close();
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
//                if(stmt!=null)
//                    stmt.close();
                if(pStmt!=null)
                    pStmt.close();
            }catch(SQLException se2){
            } // nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        return userList;
    }


    public static void createUser (String username, String password, String email, String gender, String age) {
        User user = new User(username, password, email, gender, age);

        Connection conn = null;
        //Statement stmt = null;
        PreparedStatement pStmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            String sqlCreateUser = "INSERT INTO users (username, password, email, gender, age) VALUES (?,?,?,?,?)";
            pStmt = conn.prepareStatement(sqlCreateUser);
            pStmt.setString(1, user.getUsername());
            pStmt.setString(2, user.getPassword());
            pStmt.setString(3, user.getEmail());
            pStmt.setString(4, user.getGender());
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
            pStmt.setDate(5, sqlDate);
            pStmt.execute();

            //STEP 6: Clean-up environment
            //stmt.close();
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
//                if(stmt!=null)
//                    stmt.close();
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

//    public static void main(String[] args) {
//        createUser("anders", "123456", "a@a.com", "male", "");
//    }
}