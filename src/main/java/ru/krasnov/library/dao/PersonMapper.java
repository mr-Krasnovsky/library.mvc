package ru.krasnov.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.krasnov.library.models.Person;

public class PersonMapper implements RowMapper {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
	Person person = new Person();

	person.setId(rs.getInt("person_id"));
	person.setName(rs.getString("name"));
	person.setAge(rs.getInt("age"));
	return person;
    }

}
