package cpsc304.database;

import cpsc304.controller.Passenger;
import cpsc304.model.entities.PassengerCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassengerHandler {

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private Connection connection;

    public PassengerHandler(Connection connection) {
        this.connection = connection;
    }

    public void insertPassengerCard(PassengerCard passengerCard) {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO passenger_card1 VALUES (?,?,?,?,?,?)");
            ps.setInt(1, passengerCard.getSIN());
            ps.setString(2, passengerCard.getPhone());
            ps.setString(3, passengerCard.getUserID());
            ps.setString(4, passengerCard.getEmail());
            ps.setInt(5, passengerCard.getPIN());
            ps.setString(6, passengerCard.getCardNo());
            ps.executeUpdate();
            connection.commit();
            ps.close();
            PreparedStatement ps1 = connection.prepareStatement("INSERT INTO passenger_card2 VALUES (?,?)");
            ps1.setString(1, passengerCard.getPhone());
            ps1.setString(2, passengerCard.getName());
            ps1.executeUpdate();
            connection.commit();
            ps1.close();
            System.out.println("SUCCESS!");
        } catch(SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    //project operation on the cardBalance by joining PassengerCard and Card Table
    public PassengerCard getCardAccount(String user_id) {
        ArrayList<PassengerCard> result = new ArrayList<PassengerCard>();

        try{
            String query = "SELECT sin, PASSENGER_CARD1.phone, user_id, email, pin, name, PASSENGER_CARD1.card_num, balance from PASSENGER_CARD1, PASSENGER_CARD2, CARD WHERE USER_ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                PassengerCard cardDetail = new PassengerCard( rs.getInt("sin"),
                rs.getString("phone"),
                rs.getString("user_id"),
                rs.getString("email"),
                rs.getInt("pin"),
                rs.getString("name"),
                rs.getString("card_num"),
                rs.getDouble("balance"));
                result.add(cardDetail);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            rollbackConnection();
        }
        return result.isEmpty() ? null : result.get(0);
    }

    public boolean verifyUser(String user_id){
        List<String> returnUserId = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select COUNT(1) FROM PASSENGER_CARD1 WHERE USER_ID = ?") ;
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                returnUserId.add(rs.getString("user_id"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return !returnUserId.isEmpty();
    }

    //update operation on the cardBalance by joining PassengerCard and Card Table
    public void updatePassengerCardBalance(double value) {
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE (SELECT BALANCE FROM CARD c natural join PASSENGER_CARD1 p) SET BALANCE = BALANCE + ?");
            ps.setDouble(1, value);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " does not exist!");
            }
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
