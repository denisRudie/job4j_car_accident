package ru.job4j.accident.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentServiceJpa;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentServiceJpa service;

    public AccidentControl(AccidentServiceJpa service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getAllAccidentType());
        model.addAttribute("rules", service.getAllRules());
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rulesFromUI = req.getParameterValues("rIds");
        service.saveAccident(accident, rulesFromUI);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", service.getAllAccidentType());
        model.addAttribute("rules", service.getAllRules());
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Accident current = service.getAccidentById(id);
        model.addAttribute("accident", current);
        return "accident/edit";
    }
}