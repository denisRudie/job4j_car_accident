package ru.job4j.accident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexController {

    private final AccidentService service;

    @Autowired
    public IndexController(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/accidents")
    public String index(Model model) {
        model.addAttribute("accidents", service.getAllAccidents());
        return "index";
    }
}
