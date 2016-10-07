package org.kash.tutorial.service;

import org.kash.tutorial.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.apache.commons.collections4.*;


import javax.sql.DataSource;
import java.util.List;

/**
 * Created by krangan on 10/6/2016.
 */
@Component
public class PersonDaoSpringJdbcImpl implements PersonDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<Person> getAllPerson() {

        return jdbcTemplate.query("select * from person", new PersonRowMapper());
    }

    @Override
    public Person getPerson(int id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);

        List<Person> persons= namedParameterJdbcTemplate.query("select * from person where id = :id", namedParameters, new PersonRowMapper());
        if(CollectionUtils.is`(persons)){
            return null;
        }
        return persons.get(0);
    }

    @Override
    public int getCount() {
        return jdbcTemplate.queryForObject("select count(*) from person", Integer.class);
    }
}
