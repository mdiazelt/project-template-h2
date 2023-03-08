package DAO;

import java.sql.*;
import Model.Reservation;
import Util.ConnectionSingleton;
public class ReservationDAO {

    public Reservation insertReservation(Reservation reservation) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "insert into reservation (user_id, number_guest, date_reservation) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, reservation.getUser_id());
            preparedStatement.setInt(2, reservation.getNumber_guest());
            preparedStatement.setString(3, reservation.getDate_reservation());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                int generated_reservation_id = (int) resultSet.getLong(1);
                return new Reservation(
                        generated_reservation_id,
                        reservation.user_id,
                        reservation.number_guest,
                        reservation.date_reservation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Reservation getReservationById(int id) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "select * from reservation where reservation_id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservation_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("number_guest"),
                        resultSet.getString("date_reservation"));
                return reservation;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Reservation deleteReservationById(int id) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "delete from reservation where reservation_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Reservation updateReservationById(Reservation reservation, int id) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "update reservation set date_reservation = ? where reservation_id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, reservation.getDate_reservation());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
            return getReservationById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
