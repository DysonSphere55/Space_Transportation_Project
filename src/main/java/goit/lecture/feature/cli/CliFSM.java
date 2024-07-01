package goit.lecture.feature.cli;

import goit.lecture.feature.cli.states.CliState;
import goit.lecture.feature.cli.states.IdleState;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.util.Scanner;

public class CliFSM {

    private CliState state;

    @Getter
    private Scanner scanner;

    @Getter
    private Connection connection;

    public CliFSM(Connection connection) {
        this.connection = connection;

        state = new IdleState(this);

        scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            switch (command) {
                case "exit" -> System.exit(0);
                case "addTicket" -> newTicketRequested();
                case "planetStats" -> planetStatsRequested();
                default -> unknownCommand(command);
            }
        }
    }

    public void setState(CliState state) {
        this.state = state;
        state.initState();
    }

    public void newTicketRequested() {
        state.newTicketRequested();
    }


    public void planetStatsRequested() {
        state.planetStatsRequested();
    }


    public void unknownCommand(String cmd) {
        state.unknownCommand(cmd);
    }

}
