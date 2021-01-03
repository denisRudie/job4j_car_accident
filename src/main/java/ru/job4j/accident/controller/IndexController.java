package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentServiceJpa;

@Controller
public class IndexController {

    private final AccidentServiceJpa service;

    public IndexController(AccidentServiceJpa service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", service.getAllAccidents());
        return "index";
    }
}
