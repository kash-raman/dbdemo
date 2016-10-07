package org.kash.tutorial.service;

import org.kash.tutorial.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kash on 10/6/16.
 */
public class PersonRowMapper  implements RowMapper{
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person() ;
        person.setId(resultSet.getInt(1));
       person.setFirstName(resultSet.getString(2));
        person.setLastName(resultSet.getString(3));
        return person;
    }
}
