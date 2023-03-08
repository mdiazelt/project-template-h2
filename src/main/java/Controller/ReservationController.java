package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Reservation;
import Service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class ReservationController {
    AccountService accountService;

    public ReservationController() {

        this.accountService = new AccountService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::registerNewUser);
        app.post("/login", this::userLogin);
        app.get("accounts/{account_id}/reservations", this::getReservationForAccount);

        return app;
    }


    private void registerNewUser(Context ctx) {
        ObjectMapper mapper = new ObjectMapper();
    }


    private void userLogin(Context context) {
    }

    private void getReservationForAccount(Context context) {
    }
}