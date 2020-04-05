package cpsc304.database;

import cpsc304.model.entities.Card;
import cpsc304.model.entities.PassengerCard1;
import cpsc304.model.entities.PassengerCard2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import cpsc304.database.DatabaseConnectionHandler;

public class PassengerHandler {

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";



    //Insert
    public static void insertPassengerCard(PassengerCard1 p1, PassengerCard2 p2, Card card, Connection connection) {
        Random random = new Random();
        String cardNo = String.format("%04d", random.nextInt(10000));
        int pin = random.nextInt(999);

        try {
            PreparedStatement ps1 = connection.prepareStatement("INSERT INTO passenger_card1 VALUES (?,?,?,?,?,?,?)");
            ps1.setInt(1, p1.getPIN());
            ps1.setString(2, p1.getPhone());
            ps1.setString(3, p1.getUserID());
            ps1.setString(4, p1.getEmail());
            ps1.setInt(5, p1.getAge());
            ps1.setInt(6, pin);
            ps1.setString(7, cardNo);

            ps1.executeUpdate();
            connection.commit();
            ps1.close();
            System.out.println("Insert PassengerCard1 SUCCESS!");
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());

            rollbackConnection(connection);
        }
        try {
            PreparedStatement ps2 = connection.prepareStatement("INSERT INTO PASSENGER_CARD2 VALUES (?,?)");
            ps2.setString(1, p2.getPhone());
            ps2.setString(2, p2.getName());

            ps2.executeUpdate();
            connection.commit();
            ps2.close();
            System.out.println("Insert PassengerCard2 SUCCESS!");
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection(connection);
        }

        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO card VALUES (?,?, ?)");
            ps.setString(1, cardNo);
            ps.setDouble(2, 0);
            int cvn = random.nextInt(999);
            ps.setInt(3, cvn);
            System.out.println("Add Card SUCCESS!");
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection(connection);
        }
    }


    //Delete
    public void deletePassengerCard(String user_id, Connection connection) {
        try {
            String delete = "delete from PASSENGER_CARD1 where USER_ID = " + user_id ;
            PreparedStatement ps = connection.prepareStatement(delete);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Passenger " + user_id + " does not exist!");
            }
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection(connection);
        }
    }

    //Update with String
    public static void updatePassengerStr(String attribute, String user_id, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE PASSENGER_CARD1 SET ? = ? WHERE USER_ID = ?");
            ps.setString(1, attribute);
            ps.setString(2, attribute);
            ps.setString(2, user_id);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Passenger " + user_id + " does not exist!");
            }

            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection(connection);
        }
    }


    //update with Integer
    public static void updatePassengerCardInt(int attribute, String user_id, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE PASSENGER_CARD1 SET ? = ? WHERE USER_ID = ?");
            ps.setInt(1, attribute);
            ps.setInt(1, attribute);
            ps.setString(2, user_id);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Passenger " + user_id + " does not exist!");
            }

            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection(connection);
        }
    }

    //update operation on the cardBalance by joining PassengerCard and Card Table
    public static void updatePassengerCardBalance(double value, String user_id, Connection connection) {
        try{
            PreparedStatement ps = connection.prepareStatement("UPDATE CARD " +
                    "SET BALANCE = BALANCE + ? " +
                    "WHERE CARD.CARD_NUM = (SELECT p.card_num FROM PASSENGER_CARD1 p where p.USER_ID = ?)");
            ps.setDouble(1, value);
            ps.setString(2, user_id);
            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " does not exist!");
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection(connection);
        }
    }


    //Select All query
    public static Vector<Vector<String>>  getAllPassenger(Connection connection) {

        Vector<Vector<String>> Passengers = new Vector<>();

        try {
            String select = "select P2.Name AS Name, P1.SIN AS SIN, P1.card_num AS CardNum, P1.PIN AS PIN, P1.USER_ID AS UserID, P1.EMAIL AS Email, P1.PHONE AS Phone, C.Balance AS Balance" +
                    " from PASSENGER_CARD1 P1, PASSENGER_CARD2 P2, Card C " +
                    "where P1.PHONE = P2.PHONE AND P1.CARD_NUM = C.CARD_NUM";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(select);

            while(rs.next()) {
                Vector<String> tuple = new Vector<>();
                tuple.add(rs.getString("Name"));
                tuple.add(String.valueOf(rs.getInt("SIN")));
                tuple.add(rs.getString("CardNum"));
                tuple.add(String.valueOf(rs.getInt("PIN")));
                tuple.add(rs.getString("UserID"));
                tuple.add(rs.getString("Email"));
                tuple.add(rs.getString("Phone"));
                Passengers.add(tuple);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection(connection);
        }
        return Passengers;
    }




    protected static Vector<String> getPassengerCardColumn(Connection connection){

        Vector<String> column = new Vector<>();

        try {
            Statement stmt = connection.createStatement();
            String select = "select P2.Name AS Name, P1.SIN AS SIN, P1.card_num AS CardNum, P1.PIN AS PIN, P1.USER_ID AS UserID, P1.EMAIL AS Email, P1.PHONE AS Phone" +
            " from PASSENGER_CARD1 P1, PASSENGER_CARD2 P2 " +
                    "where P1.PHONE = P2.PHONE ";
            ResultSet rs = stmt.executeQuery(select);

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name
                column.add(rsmd.getColumnName(i+1));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return column;
    }

    private static void rollbackConnection(Connection connection) {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }


    //project operation on the account detail by joining PassengerCard and Card Table
//    public PassengerCard1 getCardAccount(String user_id) {
//        Vector<Object> columnNames = new Vector<Object>();
//        Vector<Object> data = new Vector<Object>();
//        ArrayList<PassengerCard1> result = new ArrayList<PassengerCard1>();
//
//        try{
//            String query = "SELECT sin, PASSENGER_CARD1.phone, user_id, email, pin, name, PASSENGER_CARD1.card_num, balance from PASSENGER_CARD1, PASSENGER_CARD2, CARD WHERE USER_ID = ?";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, user_id);
//            ps.setFetchSize(1);
//            ResultSet rs = ps.executeQuery(query);
//            ResultSetMetaData md = rs.getMetaData();
//            int columns = md.getColumnCount();
//
//            //  Get column names
//
//            for (int i = 1; i <= columns; i++)
//            {
//                columnNames.addElement( md.getColumnName(i) );
//            }
//
//            //  Get row data
//
//            while (rs.next())
//            {
//                Vector<Object> row = new Vector<Object>(columns);
//
//                for (int i = 1; i <= columns; i++)
//                {
//                    row.addElement( rs.getObject(i) );
//                }
//
//                data.addElement( row );
//            }
//
//            rs.close();
//            ps.close();
//            connection.close();
////            while (rs.next()) {
////                PassengerCard cardDetail = new PassengerCard( rs.getInt("sin"),
////                rs.getString("phone"),
////                rs.getString("user_id"),
////                rs.getString("email"),
////                rs.getInt("pin"),
////                rs.getString("name"),
////                rs.getString("card_num"),
////                rs.getDouble("balance"));
////                result.add(cardDetail);
////            }
////            rs.close();
////            ps.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            rollbackConnection();
//        }
//        return result.get(0);
//    }



}
