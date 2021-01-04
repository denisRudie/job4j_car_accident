package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.springdatajpa.AccidentRepository;
import ru.job4j.accident.repository.springdatajpa.RuleRepository;
import ru.job4j.accident.repository.springdatajpa.TypeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccidentServiceJpa implements AccidentServiceInterface {

    private final AccidentRepository accidentRepository;
    private final RuleRepository ruleRepository;
    private final TypeRepository typeRepository;

    public AccidentServiceJpa(AccidentRepository accidentRepository, RuleRepository ruleRepository, TypeRepository typeRepository) {
        this.accidentRepository = accidentRepository;
        this.ruleRepository = ruleRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Accident> getAllAccidents() {
        return new ArrayList<>(accidentRepository.getAllAccidents());
    }

    @Override
    public List<AccidentType> getAllAccidentType() {
        List<AccidentType> types = new ArrayList<>();
        typeRepository.findAll().forEach(types::add);
        return types;
    }

    @Override
    public List<Rule> getAllRules() {
        List<Rule> rules = new ArrayList<>();
        ruleRepository.findAll().forEach(rules::add);
        return rules;
    }

    @Override
    public Accident getAccidentById(int id) {
        return accidentRepository.getAccidentById(id);
    }

    @Override
    @Transactional
    public void saveAccident(Accident accident, String[] rulesFromUI) {
        List<Rule> rules = new ArrayList<>();
        Arrays.stream(rulesFromUI)
                .forEach(r -> rules.add(Rule.of(
                        Integer.parseInt(r),
                        ruleRepository.findById(Integer.parseInt(r)).get().getName()))
                );
        accident.setRules(rules);

        AccidentType type = accident.getType();
        String typeName = typeRepository.findById(type.getId()).get().getName();
        type.setName(typeName);
        accident.setType(type);

        accidentRepository.save(accident);
    }
}
