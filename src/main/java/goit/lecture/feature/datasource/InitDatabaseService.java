package goit.lecture.feature.datasource;

import org.flywaydb.core.Flyway;

public class InitDatabaseService {
    public void init() {
        Flyway flyway = Flyway
                .configure()
                .dataSource(ConnectionProvider.DB_URL, null,  null)
                .load();

        flyway.migrate();
    }
}
