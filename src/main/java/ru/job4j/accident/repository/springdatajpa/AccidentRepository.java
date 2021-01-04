package ru.job4j.accident.repository.springdatajpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("select a from Accident a left join fetch a.type left join fetch a.rules")
    List<Accident> getAllAccidents();

    @Query("select a from Accident a left join fetch a.type left join fetch a.rules where a.id =:id")
    Accident getAccidentById(@Param("id") int id);
}
