package org.example.data;

import jakarta.annotation.PostConstruct;
import org.example.config.TestConfig;
import org.example.model.Person;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataInitializer {
    private final SimpleJdbcInsert insertPerson;

    public DataInitializer(DataSource dataSource) {
        this.insertPerson = new SimpleJdbcInsert(dataSource)
                .withTableName("t_person");
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i<TestConfig.PERSONS_AMOUNT; i++) {
            var person = new Person();
            person.setId(i);
            person.setFirstName("John " + i);
            person.setLastName("Malkovich " + i);
            addPerson(person);
        }

        System.out.println("Created " + TestConfig.PERSONS_AMOUNT + " persons.");
    }

    private void addPerson(Person person) {
        var parameters = new BeanPropertySqlParameterSource(person);
        insertPerson.execute(parameters);
    }

}
