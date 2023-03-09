package Controller;

import Service.ReservationService;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Reservation;
import Service.AccountService;
import io.javalin.Javalin;
import java.io.ObjectStreamException;
import io.javalin.http.Context;

public class ReservationController {
    AccountService accountService;
    ReservationService reservationService;

    public ReservationController() {

        this.accountService = new AccountService();
        this.reservationService = new ReservationService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/register", this::registerNewUser);
        app.post("/login", this::userLogin);
        //app.get("accounts/{account_id}/reservations", this::getReservationForAccount);
        app.post("/reservations", this::postReservationsHandler);
        app.get("/reservations/{reservation_id}", this::getReservationsByIdHandler);
        app.delete("/reservations/{reservation_id}", this::deleteReservationsHandler);
        app.patch("/reservations/{reservation_id}", this::patchReservationsHandler);

        return app;
    }
        private void registerNewUser (Context ctx) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Account newUser = mapper.readValue(ctx.body(), Account.class);
            Account addedUser = accountService.createAccount(newUser);
            if(addedUser != null) {
                ctx.json(mapper.writeValueAsString(addedUser));
            }else{
                ctx.status(400);
            }
        }
        private void userLogin (Context ctx) throws JsonProcessingException{
            ObjectMapper mapper = new ObjectMapper();
            Account verifyAccount = mapper.readValue(ctx.body(), Account.class);
            Account checkedAccount = accountService.login(verifyAccount);
            if(checkedAccount != null){
                ctx.json(mapper.writeValueAsString(checkedAccount));
            }else{
                ctx.status(401);
            }
        }

        private void postReservationsHandler (Context context) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Reservation reservation = mapper.readValue(context.body(), Reservation.class);
            Reservation addedReservation = reservationService.insertReservation(reservation);
            if (addedReservation != null) {
                context.json(addedReservation);
            } else {
                context.status(400);
            }
        }
        private void getReservationsByIdHandler (Context context) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            int id = Integer.parseInt(context.pathParam("reservation_id"));
            Reservation gotReservation = reservationService.getReservationById(id);
            if (gotReservation != null) {
                context.json(mapper.writeValueAsString(gotReservation));
            } else {
                context.status();
            }
        }

        private void deleteReservationsHandler (Context context){
            String id = context.pathParam("reservation_id");
            Reservation deletedReservation = reservationService.deleteReservationById(Integer.parseInt(id));
            if (deletedReservation != null) {
                context.json(deletedReservation);
            } else {
                context.status(200);
            }
        }

        private void patchReservationsHandler (Context context) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Reservation reservation = mapper.readValue(context.body(), Reservation.class);
            int id = Integer.parseInt(context.pathParam("reservation_id"));
            Reservation updatedReservation = reservationService.updateReservationById(reservation, id);
            if (updatedReservation != null) {
                context.json(updatedReservation);
            } else {
                context.status(400);
            }
        }

        /*
        private void getReservationForAccount (Context ctx) throws JsonProcessingException{
            int account_id = Integer.parseInt(ctx.pathParam("account_id"));
            List<Reservation> reservation = reservationService.getReservationByAccount(account_id);
            ctx.json(messages);
        }*/
}