package Controller;

<<<<<<< HEAD
import Service.ReservationService;

=======
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Reservation;
import io.javalin.Javalin;
//import io.javalin.http.ConnectionSingleton;

public class ReservationController {
    CreateAccount createAccount;
    ReservationService reservationService;

    public ReservationController() {

        this.createAccount = new CreateAccount();
        this.reservationService = new ReservationService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/reservations", this::postReservationsHandler);
        app.get("")

        //app.post("/register", this::registerNewUser);

        return app;
    }

    //private void registerNewUser(Contex ctx) {
        ObjectMapper mapper = new ObjectMapper();
    }
}