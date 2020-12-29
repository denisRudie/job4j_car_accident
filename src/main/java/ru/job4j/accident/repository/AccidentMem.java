package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, Accident.of(1, "acc1", "text1", "address1"));
        accidents.put(2, Accident.of(2, "acc2", "text2", "address2"));
        accidents.put(3, Accident.of(3, "acc3", "text3", "address3"));
        accidents.put(4, Accident.of(4, "acc4", "text4", "address4"));
        accidentTypes.put(1, AccidentType.of(1, "Две машины"));
        accidentTypes.put(2, AccidentType.of(2, "Машина и человек"));
        accidentTypes.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public List<AccidentType> findAllAccidentTypes() {
        return new ArrayList<>(accidentTypes.values());
    }

    public void create(Accident accident) {
        accident.setId(this.accidents.size() + 1);
        AccidentType type = accident.getType();
        String typeName = getAccidentTypeById(type.getId()).getName();
        type.setName(typeName);
        this.accidents.put(accident.getId(), accident);
    }

    public void update(Accident accident) {
        Accident current = accidents.get(accident.getId());
        AccidentType type = accident.getType();
        String typeName = getAccidentTypeById(type.getId()).getName();
        type.setName(typeName);
        current.setType(type);
        current.setName(accident.getName());
        current.setAddress(accident.getAddress());
        current.setText(accident.getText());
    }

    public Accident getAccidentById(int id) {
        return accidents.get(id);
    }

    public AccidentType getAccidentTypeById(int id) {
        return accidentTypes.get(id);
    }
}
