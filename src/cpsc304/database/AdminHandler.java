package cpsc304.database;

import java.sql.Connection;

public class AdminHandler {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private Connection connection;

    public AdminHandler(Connection connection) {
        this.connection = connection;
    }

}
