package Service;

import DAO.ReservationDAO;
import Model.Reservation;

public class ReservationService {
    private ReservationDAO reservationDAO;

    public ReservationService() {
        reservationDAO = new ReservationDAO();
    }
    public Reservation insertReservation(Reservation reservation) {
        return reservationDAO.insertReservation(reservation);
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
}
