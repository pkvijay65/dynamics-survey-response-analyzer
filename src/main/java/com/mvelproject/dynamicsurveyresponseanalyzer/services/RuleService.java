package com.mvelproject.dynamicsurveyresponseanalyzer.services;

import com.mvelproject.dynamicsurveyresponseanalyzer.entities.Rule;
import com.mvelproject.dynamicsurveyresponseanalyzer.repositories.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {
    private final RuleRepository ruleRepository;
    @Autowired
    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public void addRule(Rule rule) {
        ruleRepository.save(rule);
    }

    public Rule getRuleByName(String name) {
        return ruleRepository.findByName(name).orElseThrow(() -> new RuntimeException("Rule not found"));
    }

    public Object findAll() {
        return ruleRepository.findAll();
    }

    public void save(Rule rule) {
        ruleRepository.save(rule);
    }

    public void delete(Long id) {
        ruleRepository.deleteById(id);
    }

    // Other methods as needed
}

