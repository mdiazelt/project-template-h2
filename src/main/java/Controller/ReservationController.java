package Controller;

import Service.ReservationService;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Reservation;
import io.javalin.Javalin;

import io.javalin.http.Context;

import java.io.ObjectStreamException;
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
        app.get("/reservations/{reservation_id}", this::getReservationsByIdHandler);
        app.delete("/reservations/{reservation_id}", this::deleteReservationsHandler);
        app.patch("/reservations/{reservation_id}", this::patchReservationsHandler);

        //app.post("/register", this::registerNewUser);

        return app;
    }

    private void registerNewUser(Contex ctx) {
        ObjectMapper mapper = new ObjectMapper();
    }

    private void postReservationsHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Reservation reservation = mapper.readValue(context.body(), Reservation.class);
        Reservation addedReservation = reservationService.insertReservation(reservation);
        if (addedReservation != null) {
            context.json(addedReservation);
        } else {
            context.status(400);
        }
    }
    private void getReservationsByIdHandler(Context context) {
        String id = context.pathParam("reservation_id");
        Reservation gotReservation = reservationService.getReservationById(Integer.parseInt(id));
        if (gotReservation != null) {
            context.json(gotReservation);
        } else {
            context.status(404);
        }
    }

    private void deleteReservationsHandler(Context context) {
        String id = context.pathParam("reservation_id");
        Reservation deletedReservation = reservationService.getReservationById(Integer.parseInt(id));
        if (deletedReservation != null) {
            context.json(deletedReservation);
        } else {
            context.status(200);
        }
    }

    private void patchReservationsHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Reservation reservation = mapper.readValue(context.body(), Reservation.class);
        int id = Integer.parseInt(.pathParam("reservation_id"));
        Reservation updatedReservation = reservationService.updateReservationById(reservation, id);
        if (updatedReservation != null) {
            context.json(updatedReservation);
        } else {
            context.status(400);
        }
    }
}