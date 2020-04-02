package cpsc304.database;

import cpsc304.model.entities.Card;
import cpsc304.model.entities.PassengerCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerHandler {

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private Connection connection;

    public PassengerHandler(Connection connection) {
        this.connection = connection;
    }

    public void insertPassengerCard(PassengerCard passengerCard) {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO passenger_card1 VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, passengerCard.getSIN());
            ps.setString(2, passengerCard.getPhone());
            ps.setString(3, passengerCard.getUserID());
            ps.setString(4, passengerCard.getEmail());
            ps.setInt(5, passengerCard.getPIN());
            ps.setString(6, passengerCard.getCardNo());
            ps.setString(7, passengerCard.getName());
            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch(SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    //project operation on the cardBalance by joining PassengerCard and Card Table
    public void viewPassengerCardBalance(PassengerCard passengerCard) {
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT balance FROM passenger_card1, card WHERE passenger_card1.card_num = card.card_num");
            ResultSet rs = ps.executeQuery();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    //update operation on the cardBalance by joining PassengerCard and Card Table
    public void updatePassengerCardBalance(double newBalance) {
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE card SET card.balance = ?");
            ps.setDouble(1, newBalance);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }



    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }



}
