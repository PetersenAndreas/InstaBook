package Database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLDBConnection {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static final String PROPERTIESFILEPATH = "/SQL.properties";
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static Connection conn;

    public static Connection getConnection() throws SQLException {

        try (InputStream f = SQLDBConnection.class.getResourceAsStream(PROPERTIESFILEPATH)) {
            Properties pros = new Properties();
            pros.load(f);
            URL = pros.getProperty("url");
            USER = pros.getProperty("user");
            PASSWORD = pros.getProperty("password");
        } catch (IOException ex) {
            Logger.getLogger(SQLDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn = DriverManager.getConnection(URL, USER, PASSWORD);

        return conn;
    }
}