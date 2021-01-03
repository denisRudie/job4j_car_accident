package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

public interface AccidentServiceInterface {

    List<Accident> getAllAccidents();

    List<AccidentType> getAllAccidentType();

    List<Rule> getAllRules();

    Accident getAccidentById(int id);

    void saveAccident(Accident accident, String[] rulesFromUI);
}
