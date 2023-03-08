import Util.ConnectionSingleton;
import org.h2.tools.RunScript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
//        this line is just for testing that your tables get set up correctly
//        if not, you'll get a stack trace
        ConnectionSingleton.getConnection();
    }

    private static String url = "jdbc:h2:./h2/db";
    private static String username = "sa";
    private static String password = "sa";
    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
                resetTestDataBase();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void resetTestDataBase() {
        if (connection == null) {
            getConnection();
        } else {
            try {
                FileReader sqlReader = new FileReader("src/main/resources/Tables.sql");
                RunScript.execute(connection, sqlReader);
            } catch (SQLException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
