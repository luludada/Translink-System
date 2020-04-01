package database;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionHandler {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static final String username = "ora_ymy0801";
    private static final String password = "a87216792";

    private Connection connection = null;

//    public DatabaseConnectionHandler() {
//        try {
//            // Use this version of the ORACLE_URL if you are running the code off of the server
//            //private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
//            // Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
//            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//    }
//
//
//    public void close() {
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//        }
//    }



}
