package com.mvelproject.dynamicsurveyresponseanalyzer.controllers;

import com.mvelproject.dynamicsurveyresponseanalyzer.entities.Rule;
import com.mvelproject.dynamicsurveyresponseanalyzer.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class RuleController {

    private final RuleService ruleService;

    @Autowired
    public RuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping("/addRule")
    public String addRuleForm() {
        return "addRule";
    }

    @PostMapping("/save")
    public String saveRule(@ModelAttribute Rule rule) {
        ruleService.save(rule);
        return "redirect:/rules";
    }

    @GetMapping("/delete/{id}")
    public String deleteRule(@PathVariable Long id) {
        ruleService.delete(id);
        return "redirect:/rules";
    }

    // Add methods for edit and delete
}

