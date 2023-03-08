package Controller;

import Service.ReservationService;


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


        return app;
    }
}