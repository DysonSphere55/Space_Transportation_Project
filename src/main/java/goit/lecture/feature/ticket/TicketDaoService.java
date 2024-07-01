package goit.lecture.feature.ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketDaoService {

    private PreparedStatement createSt;
    private PreparedStatement getLatestIdSt;
    private PreparedStatement getTicketCountSt;



    public TicketDaoService(Connection connection) throws SQLException {
        createSt = connection.prepareStatement("""
                INSERT INTO ticket (passenger_id, planet_from, planet_to) VALUES (?, ?, ?)""");
        getLatestIdSt = connection.prepareStatement("""
                SELECT max(id) as maxId FROM ticket""");
        getTicketCountSt = connection.prepareStatement("""
                SELECT count(*) AS ticketCount FROM ticket WHERE planet_to = ?""");

    }

    public long create(TicketEntity ticket) throws SQLException {
        createSt.setLong(1, ticket.getPassengerId());
        createSt.setString(2, ticket.getFrom().name());
        createSt.setString(3, ticket.getTo().name());
        createSt.executeUpdate();
        try (ResultSet rs = getLatestIdSt.executeQuery()) {
            if (!rs.next()) {
                return -1;
            }
            return rs.getLong("maxId");
        }
    }

    public long getTicketCountToPlanet(Planet planet) throws SQLException {
        getTicketCountSt.setString(1, planet.name());
        try (ResultSet rs = getTicketCountSt.executeQuery()) {
            if (!rs.next()) {
                return -1;
            }
            return rs.getLong("ticketCount");
        }
    }
}
