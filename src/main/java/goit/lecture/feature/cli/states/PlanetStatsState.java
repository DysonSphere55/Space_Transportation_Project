package goit.lecture.feature.cli.states;

import goit.lecture.feature.cli.CliFSM;
import goit.lecture.feature.cli.PlanetChooser;
import goit.lecture.feature.ticket.Planet;
import goit.lecture.feature.ticket.TicketDaoService;

import java.sql.SQLException;

public class PlanetStatsState extends CliState {
    public PlanetStatsState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void initState() {
        System.out.println("Enter planet: ");

        Planet planet = new PlanetChooser(fsm.getScanner()).ask();

        try {
            TicketDaoService daoService = new TicketDaoService(fsm.getConnection());
            long ticketCount = daoService.getTicketCountToPlanet(planet);
            System.out.println(planet.name() + " found. Ticket count: " + ticketCount);
            fsm.setState(new IdleState(fsm));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
