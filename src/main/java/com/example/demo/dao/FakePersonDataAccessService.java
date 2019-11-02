package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeDAO")
public class FakePersonDataAccessService implements PersonDAO {

    private static Map<UUID, Person> DB = new HashMap<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.put(id, new Person(id, person.getName()));
        return 1;
    }

    @Override
    public int deletePersonById(UUID id) {
        return DB.remove(id) == null ? 0 : 1;
    }

    @Override
    public int updatePersonById(UUID id, Person updatedPerson) {
        if(DB.containsKey(id)) {
            DB.replace(id, new Person(id, updatedPerson.getName()));
            return 1;
        }
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return Optional.ofNullable(DB.get(id));
    }

    @Override
    public Collection<Person> selectAllPeople() {
        return DB.values();
    }

}
