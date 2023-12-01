package com.mvelproject.dynamicsurveyresponseanalyzer.repositories;

import com.mvelproject.dynamicsurveyresponseanalyzer.entities.Survey;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SurveyRepository {
    private Map<String, Survey> surveys = new HashMap<>();

    public void saveSurvey(Survey survey) {
        surveys.put(survey.getId(), survey);
    }

    public Survey getSurvey(String id) {
        return surveys.get(id);
    }

    // Additional methods as needed
}
