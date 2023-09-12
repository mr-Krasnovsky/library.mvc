package ru.krasnov.library.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.krasnov.library.models.Person;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
	return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Optional<Person> show(String name) {
	return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[] { name }, new PersonMapper())
		.stream().findAny();
    }

    public Person show(int person_id) {
	return (Person) jdbcTemplate
		.query("SELECT * FROM Person WHERE person_id=?", new Object[] { person_id }, new PersonMapper())
		.stream().findAny().orElse(null);
    }

    public void save(Person person) {
	jdbcTemplate.update("INSERT INTO Person(name, age) VALUES(?, ?)", person.getName(), person.getAge());
    }

    public void update(int person_id, Person updatedPerson) {
	jdbcTemplate.update("UPDATE Person SET name=?, age=? WHERE person_id=?", updatedPerson.getName(),
		updatedPerson.getAge(), person_id);
    }

    public void delete(int person_id) {
	jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", person_id);
    }
}