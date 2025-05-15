package org.example.config.data;

import jakarta.annotation.PostConstruct;
import org.example.entity.Person;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataInitializer {
    private static final int PERSONS_AMOUNT = 1_000_000;

    private final SimpleJdbcInsert insertPerson;

    public DataInitializer(DataSource dataSource) {
        this.insertPerson = new SimpleJdbcInsert(dataSource)
                .withTableName("t_person")
                .usingGeneratedKeyColumns("id");
    }

    @PostConstruct
    public void init() {
        for (int i=0; i<PERSONS_AMOUNT; i++) {
            var person = new Person();
            person.setFirstName("John " + i);
            person.setLastName("Malkovich " + i);
            addPerson(person);
        }
    }

    private void addPerson(Person person) {
        var parameters = new BeanPropertySqlParameterSource(person);
        insertPerson.execute(parameters);
    }

}
