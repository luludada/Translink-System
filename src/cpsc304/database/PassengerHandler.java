package cpsc304.database;

import java.sql.Connection;

public class PassengerHandler {

    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private Connection connection;

    public PassengerHandler(Connection connection) {
        this.connection = connection;
    }




}