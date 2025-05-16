package org.example.data;

import org.example.model.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcPersonDao {
    private final NamedParameterJdbcTemplate template;

    public JdbcPersonDao(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public Person getPerson(int id) {
        return template.queryForObject("SELECT id, first_name, last_name FROM t_person WHERE id = :id",
                new MapSqlParameterSource("id", id), BeanPropertyRowMapper.newInstance(Person.class));
    }

}
