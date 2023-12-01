package com.mvelproject.dynamicsurveyresponseanalyzer.repositories;

import com.mvelproject.dynamicsurveyresponseanalyzer.entities.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    Optional<Rule> findByName(String name);
}
