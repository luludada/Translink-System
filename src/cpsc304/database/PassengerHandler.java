package cpsc304.database;

import cpsc304.model.entities.Card;
import cpsc304.model.entities.Driver;
import cpsc304.model.entities.PassengerCard;

import java.sql.*;

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
    public void viewCardAccount() {
        try{
            String query = "select sin, PASSENGER_CARD1.phone, user_id, email, pin, name, PASSENGER_CARD1.card_num, balance from PASSENGER_CARD1, PASSENGER_CARD2, CARD";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("------------------------------------------------");
                System.out.println("SIN: " + rs.getInt("sin"));
                System.out.println("phone: " + rs.getString("phone"));
                System.out.println("user_id: " + rs.getString("user_id"));
                System.out.println("email: " + rs.getString("email"));
                System.out.println("pin: " + rs.getInt("pin"));
                System.out.println("name: " + rs.getString("name"));
                System.out.println("card_num: " + rs.getString("card_num"));
                System.out.println("balance: " + rs.getDouble("balance"));
                System.out.println("------------------------------------------------");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
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
