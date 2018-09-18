package mariannus.db;

/**
 * Created by Tim on 8.4.2016.
 */
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

class Conection {
    private static Conection instance = new Conection();
    private static final String URL = "jdbc:mysql://192.168.0.181/mdata";
//    private static final String URL = "jdbc:mysql://localhost/mdata";
    private static final String USER = "root";
    private static final String PASSWORD = "drevorubac";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private Conection(){
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private java.sql.Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    static Connection getConnection() {
        return instance.createConnection();
    }
}
