package goit.lecture.feature.cli.states;

import goit.lecture.feature.cli.CliFSM;
import goit.lecture.feature.cli.PlanetChooser;
import goit.lecture.feature.passenger.PassengerDaoService;
import goit.lecture.feature.passenger.PassengerEntity;
import goit.lecture.feature.ticket.Planet;
import goit.lecture.feature.ticket.TicketDaoService;
import goit.lecture.feature.ticket.TicketEntity;

import java.sql.SQLException;
import java.util.Scanner;

public class TicketOrderState extends CliState {
    public TicketOrderState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void initState() {
        System.out.println("Enter passenger passport: ");

        Scanner scanner = fsm.getScanner();
        String userPassportId = scanner.nextLine();

        try {
            PassengerDaoService passengerDaoService = new PassengerDaoService(fsm.getConnection());
            PassengerEntity passenger = passengerDaoService.getByPassportId(userPassportId);

            if (passenger == null) {
                System.out.println("Enter passenger passenger name:");
                String userPassengerName = scanner.nextLine();
                passenger = new PassengerEntity();
                passenger.setName(userPassengerName);
                passenger.setPassportId(userPassportId);
                passengerDaoService.create(passenger);
                System.out.println("Passenger saved.");
            } else {
                System.out.println("Passenger " + passenger.getName() + " found.");
            }

            passenger = passengerDaoService.getByPassportId(userPassportId);

            System.out.println("Enter FROM planet:");
            Planet userFromPlanet = new PlanetChooser(scanner).ask();

            System.out.println(userFromPlanet.name() + " found. Enter TO planet:");
            Planet userToPlanet = new PlanetChooser(scanner).ask();

            TicketEntity ticket = new TicketEntity();
            ticket.setPassengerId(passenger.getId());
            ticket.setFrom(userFromPlanet);
            ticket.setTo(userToPlanet);


            TicketDaoService ticketDaoService = new TicketDaoService(fsm.getConnection());
            long ticketId = ticketDaoService.create(ticket);

            System.out.println(userToPlanet.name() + " found. Ticket ordered with ID: " + ticketId);

            fsm.setState(new IdleState(fsm));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
