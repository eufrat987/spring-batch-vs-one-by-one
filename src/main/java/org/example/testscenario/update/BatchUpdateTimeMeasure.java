package org.example.testscenario.update;

import org.example.testscenario.util.TimeMeasure;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class BatchUpdateTimeMeasure extends TimeMeasure {
    private JdbcClient jdbcClient;
    private String newFirstName;

    public BatchUpdateTimeMeasure(DataSource dataSource) {
        super("Batch test");
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    @Override
    protected void execute() {
        assert newFirstName != null : "Should be already specified";

    }
}
