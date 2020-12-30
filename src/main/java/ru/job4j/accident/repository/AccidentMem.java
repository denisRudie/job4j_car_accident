package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, Accident.of(1, "acc1", "text1", "address1"));
        accidents.put(2, Accident.of(2, "acc2", "text2", "address2"));
        accidents.put(3, Accident.of(3, "acc3", "text3", "address3"));
        accidents.put(4, Accident.of(4, "acc4", "text4", "address4"));
        accidentTypes.put(1, AccidentType.of(1, "Две машины"));
        accidentTypes.put(2, AccidentType.of(2, "Машина и человек"));
        accidentTypes.put(3, AccidentType.of(3, "Машина и велосипед"));
        rules.put(1, Rule.of(1, "Rule1"));
        rules.put(2, Rule.of(2, "Rule2"));
        rules.put(3, Rule.of(3, "Rule3"));
    }

    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public List<AccidentType> findAllAccidentTypes() {
        return new ArrayList<>(accidentTypes.values());
    }

    public List<Rule> findAllRules() {
        return new ArrayList<>(rules.values());
    }

    public void create(Accident accident) {
        accident.setId(this.accidents.size() + 1);
        this.accidents.put(accident.getId(), accident);
    }

    public void update(Accident accident) {
        Accident current = accidents.get(accident.getId());
        current.setType(accident.getType());
        current.setName(accident.getName());
        current.setAddress(accident.getAddress());
        current.setText(accident.getText());
        current.setRules(accident.getRules());
    }

    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }

    public AccidentType getAccidentTypeById(int id) {
        return accidentTypes.get(id);
    }

    public Rule getRuleById(int id) {
        return rules.get(id);
    }
}
