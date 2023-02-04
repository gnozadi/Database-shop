package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //TODO replace with your user/pass
    private static final String user = "root";
    private static final String pass = "bita1379";
    private static final String url = "jdbc:mysql://localhost:3306/myshop";

    static String driver = "com.mysql.jdbc.Driver";

    public static Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("\033[0;92mConnecting to Database...\033[0m");
        } catch (SQLException ex) {
            System.out.println("\033[0;91mConnecting to database failed\033[0m");
            ex.getStackTrace();
        }
        return con;

    }
}
