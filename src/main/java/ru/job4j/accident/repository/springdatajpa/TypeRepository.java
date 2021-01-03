package ru.job4j.accident.repository.springdatajpa;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

public interface TypeRepository extends CrudRepository<AccidentType, Integer> {
}
