package cpsc304.database;

import cpsc304.model.entities.Card;
import cpsc304.model.entities.PassengerCard1;
import cpsc304.model.entities.PassengerCard2;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.io.BufferedReader;
import java.util.Vector;


/**
 * This class handles all database related translink
 */
public class DatabaseConnectionHandler {
    // Use this version of the ORACLE_URL if you are running the code off of the server
    //	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static final String username = "ora_ymy0801";
    private static final String password = "a87216792";

    private Connection connection = null;
    private DriverHandler driverHandler = null;
    private AdminHandler adminHandler = null;
    private PassengerHandler passengerHandler = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);
            //passengerHandler = new PassengerHandler(connection);
            adminHandler = new AdminHandler(connection);
            driverHandler = new DriverHandler(connection);
            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }


     void rollbackConnection() {
        try  {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void databaseSetup() {

        try {
            Statement stmt = connection.createStatement();
            File insertScript = new File("src/cpsc304/sql/insert.sql");
            BufferedReader br = new BufferedReader(new FileReader(insertScript));
            String line;
            while ((line=br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    stmt.addBatch(line);
                }
            }
            int[] updateCounts = stmt.executeBatch();
            connection.commit();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }


    }

    /* Insert operation in database */
    public void insertPassengerCard(PassengerCard1 p1, PassengerCard2 p2, Card card) {
        PassengerHandler.insertPassengerCard(p1, p2, card, connection);
    }


    /* Delete operation in database */
    public void deletePassengerCard(String user_id) {
        passengerHandler.deletePassengerCard(user_id, connection);
    }



    /* Update operation in database */
    public void updatePassengerStr(String attribute, String user_id) {
        PassengerHandler.updatePassengerStr(attribute, user_id, connection);
    }

    public void updatePassengerCardInt(int attribute, String user_id) {
        PassengerHandler.updatePassengerCardInt(attribute, user_id, connection);
    }


    //nested update operation on update card Balance
    public void updatePassengerCardBalance(double value, String user_id) {
        PassengerHandler.updatePassengerCardBalance(value, user_id, connection);
    }


    /* Select all operation in one table in database */
    public Vector<Vector<String>> getAllPassenger() {
        return PassengerHandler.getAllPassenger(connection);
    }

    public Vector<String> getPassengerCardColumn() {
        return PassengerHandler.getPassengerCardColumn(connection);
    }

    public Vector<Vector<String>> getAllCard() {
        return PassengerHandler.getAllCard(connection);
    }

    public Vector<String> getCardColumn() {
        return PassengerHandler.getCardColumn(connection);
    }

    public Vector<Vector<String>> FindALLPassengerName() {
        return PassengerHandler.FindALLPassengerName(connection);
    }



    /*Projection operation in one table */


    /*Join operation */


    /*Aggregation query  */



    /*Nested aggregation with group-by */


    /*Division operation */


    public PassengerHandler getPassengerHandler() {
        return passengerHandler;
    }

    public DriverHandler getDriverHandler() {return driverHandler;}

    public AdminHandler getAdminHandler() {
        return adminHandler;
    }


}
