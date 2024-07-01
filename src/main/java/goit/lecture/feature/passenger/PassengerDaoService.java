package goit.lecture.feature.passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassengerDaoService {
    private PreparedStatement createSt;
    private PreparedStatement getByPassportIdSt;

    public PassengerDaoService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement("""
                INSERT INTO passenger (passport_id, name) VALUES (?, ?)""");
        getByPassportIdSt= connection.prepareStatement("""
                SELECT * FROM passenger WHERE passport_id = ?""");
    }

    public void create(PassengerEntity passenger) throws SQLException {
        createSt.setString(1, passenger.getPassportId());
        createSt.setString(2, passenger.getName());
        createSt.executeUpdate();
    }

    public PassengerEntity getByPassportId(String passportId) throws SQLException {
        getByPassportIdSt.setString(1, passportId);
        try (ResultSet rs = getByPassportIdSt.executeQuery()) {
            if (!rs.next()) {
                return null;
            }
            PassengerEntity passenger = new PassengerEntity();
            passenger.setId(rs.getLong("id"));
            passenger.setPassportId(passportId);
            passenger.setName(rs.getString("name"));
            return passenger;
        }
    }
}
