package cpsc304.database;

import java.sql.Connection;

public class DriverHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private Connection connection;

    public DriverHandler(Connection connection) {
        this.connection = connection;
    }

}