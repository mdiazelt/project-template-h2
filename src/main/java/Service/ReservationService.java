package Service;

import DAO.ReservationDAO;
import Model.Reservation;

public class ReservationService {
    private ReservationDAO reservationDAO;

    public ReservationService() {
        reservationDAO = new ReservationDAO();
    }
    public Reservation insertReservation(Reservation reservation) {
//        if (reservation.date_reservation != "" && reservation.date_reservation.length() <= 100) {
            return reservationDAO.insertReservation(reservation);
//        }
//        return null;
    }

    public Reservation getReservationById(int id) {
        return reservationDAO.getReservationById(id);
    }

    public Reservation deleteReservationById(int id) {
        Reservation reservation = reservationDAO.getReservationById(id);
        reservationDAO.deleteReservationById(id);
        if (reservation != null) {
            return reservation;
        }
        return null;
    }

    public Reservation updateReservationById(Reservation reservation, int id) {
        if (reservation.date_reservation != "" && reservation.date_reservation.length() <= 100) {
            return reservationDAO.updateReservationById(reservation, id);
        }
        return null;
    }
}
