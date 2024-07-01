package goit.lecture.feature.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionProvider {

    public static final String DB_URL = "jdbc:h2:./SpaceTransportDB";
    private List<Connection> connectionList;

    public ConnectionProvider() {
        this.connectionList = new ArrayList<>();
    }

    public Connection create() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL);
        connectionList.add(connection);
        return connection;
    }

    public void close() throws SQLException {
        for (Connection connection : connectionList) {
            connection.close();
        }
    }
}
