package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, Accident.of(1, "acc1", "text1", "address1"));
        accidents.put(2, Accident.of(2, "acc2", "text2", "address2"));
        accidents.put(3, Accident.of(3, "acc3", "text3", "address3"));
        accidents.put(4, Accident.of(4, "acc4", "text4", "address4"));
    }

    public Map<Integer, Accident> findAll() {
        return accidents;
    }
}
