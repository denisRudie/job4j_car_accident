package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {

    private final AccidentMem store;

    @Autowired
    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public List<Accident> getAllAccidents() {
        return store.findAllAccidents();
    }

    public void saveAccident(Accident accident) {
        int id = accident.getId();
        AccidentType type = accident.getType();
        String typeName = store.getAccidentTypeById(type.getId()).getName();
        type.setName(typeName);
        accident.setType(type);
        if (id != 0) {
            store.update(accident);
        } else {
            store.create(accident);
        }
    }
}
