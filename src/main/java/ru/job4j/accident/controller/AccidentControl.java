package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

@Controller
public class AccidentControl {
    private final AccidentMem store;

    public AccidentControl(AccidentMem store) {
        this.store = store;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", store.findAllAccidentTypes());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        if (accident.getId() != 0) {
            store.update(accident);
        } else {
            store.create(accident);
        }
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
