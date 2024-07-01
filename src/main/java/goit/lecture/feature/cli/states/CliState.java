package goit.lecture.feature.cli.states;

import goit.lecture.feature.cli.CliFSM;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CliState {

    protected final CliFSM fsm;

    public void initState() {

    }

    public void newTicketRequested() {

    }


    public void planetStatsRequested() {

    }


    public void unknownCommand(String cmd) {

    }
}
