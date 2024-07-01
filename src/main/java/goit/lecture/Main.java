package goit.lecture;

import goit.lecture.feature.cli.CliFSM;
import goit.lecture.feature.cli.states.CliState;
import goit.lecture.feature.datasource.ConnectionProvider;
import goit.lecture.feature.datasource.InitDatabaseService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        InitDatabaseService initDBService = new InitDatabaseService();
        initDBService.init();

        ConnectionProvider connectionProvider = new ConnectionProvider();

        Connection connection = connectionProvider.create();

        CliFSM fsm = new CliFSM(connection);

        connection.close();
    }
}