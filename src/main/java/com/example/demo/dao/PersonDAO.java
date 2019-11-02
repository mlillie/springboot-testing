package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {

    int insertPerson(UUID id, Person person);
    int deletePersonById(UUID id);
    int updatePersonById(UUID id, Person person);
    Optional<Person> selectPersonById(UUID id);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    Collection<Person> selectAllPeople();

}
