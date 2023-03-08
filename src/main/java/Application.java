import Controller.ReservationController;
import Util.ConnectionSingleton;
import io.javalin.Javalin;
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

        ReservationController controller = new ReservationController();
        Javalin app = controller.startAPI();
        app.start(8080);
    }
}
