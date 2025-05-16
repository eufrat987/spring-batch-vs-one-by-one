package org.example.testscenario.update;

import org.example.config.TestConfig;
import org.example.testscenario.util.TimeMeasure;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class OneByOneUpdateTimeMeasure extends TimeMeasure {
    private JdbcClient jdbcClient;
    private String newFirstName;

    public OneByOneUpdateTimeMeasure(DataSource dataSource) {
        super("One by one test");
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    @Override
    protected void execute() {
        assert newFirstName != null : "Should be already specified";

        for (int i = 0; i < TestConfig.PERSONS_AMOUNT; i++) {
            jdbcClient.sql("UPDATE t_person SET first_name = :firstName WHERE id = :id")
                    .param("id", i)
                    .param("firstName", newFirstName)
                    .update();
        }
    }
}
