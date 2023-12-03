package com.example.web.repositories;

import com.example.web.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    //в классе Repository писать запросы желательно только create, read, update, delete для сущности Person
    List<Person> findByName(String name);

    List<Person> findAll();

    Optional<Person> findByEmail(String email);


    public void deletePersonById(Integer id);
}
