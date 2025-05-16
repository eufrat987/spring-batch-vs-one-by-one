package org.example.testscenario.update;

import org.example.config.TestConfig;
import org.example.model.Person;
import org.example.testscenario.util.TimeMeasure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class BatchUpdateTimeMeasure extends TimeMeasure {
    private JdbcTemplate template;
    private String newFirstName;

    public BatchUpdateTimeMeasure(DataSource dataSource) {
        super("Batch test");
        this.template = new JdbcTemplate(dataSource);
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    @Override
    protected void execute() {
        assert newFirstName != null : "Should be already specified";

        List<Object[]> batch = new ArrayList<>();
        for (int i = 0; i < TestConfig.PERSONS_AMOUNT; i++) {
            Object[] values = new Object[]{"Batch", i};
            batch.add(values);
        }

        template.batchUpdate("UPDATE t_person SET first_name = ? WHERE id = ?", batch);
    }
}
