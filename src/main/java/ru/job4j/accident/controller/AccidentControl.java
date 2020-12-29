package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentService service;
    private final AccidentMem store;

    public AccidentControl(AccidentService service, AccidentMem store) {
        this.service = service;
        this.store = store;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", store.findAllAccidentTypes());
        model.addAttribute("rules", store.findAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        List<Rule> rules = new ArrayList<>();
        String[] rulesFromUI = req.getParameterValues("rIds");
        Arrays.stream(rulesFromUI)
                .forEach(r -> rules.add(Rule.of(
                        Integer.parseInt(r), "")));
        accident.setRules(rules);
        service.saveAccident(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", store.findAllAccidentTypes());
        Accident current = store.getAccidentById(id);
        model.addAttribute("accident", current);
        return "accident/edit";
    }
}
