package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate store;

    @Autowired
    public AccidentService(AccidentJdbcTemplate store) {
        this.store = store;
    }

    public List<Accident> getAllAccidents() {
        return store.findAllAccidents();
    }

    public List<AccidentType> getAllAccidentType() {
        return store.findAllAccidentTypes();
    }

    public List<Rule> getAllRules() {
        return store.findAllRules();
    }

    public Accident getAccidentById(int id) {
        return store.getAccidentById(id);
    }

    public void saveAccident(Accident accident, String[] rulesFromUI) {
        List<Rule> rules = new ArrayList<>();
        Arrays.stream(rulesFromUI)
                .forEach(r -> rules.add(Rule.of(
                        Integer.parseInt(r), store.getRuleById(Integer.parseInt(r)).getName()))
                );
        accident.setRules(rules);

        AccidentType type = accident.getType();
        String typeName = store.getAccidentTypeById(type.getId()).getName();
        type.setName(typeName);
        accident.setType(type);

        int id = accident.getId();
        if (id != 0) {
            store.update(accident);
        } else {
            store.create(accident);
        }
    }
}
