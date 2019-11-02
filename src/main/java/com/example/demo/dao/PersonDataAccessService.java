package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        final String sqlStatement = "INSERT INTO person (id, name) VALUES (?, ?)";

        return jdbcTemplate.update(sqlStatement, id, person.getName());
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sqlStatement = "DELETE FROM person WHERE id = ?";
        return jdbcTemplate.update(sqlStatement, id);
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sqlStatement = "UPDATE person SET name = ? WHERE id = ?";

        return jdbcTemplate.update(sqlStatement, person.getName(), id);
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Collection<Person> selectAllPeople() {
        final String sqlStatement = "SELECT id, name FROM person";

        return jdbcTemplate.query(sqlStatement, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }
}
