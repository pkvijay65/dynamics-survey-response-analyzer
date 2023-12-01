package com.mvelproject.dynamicsurveyresponseanalyzer.config;
//import necessary packages
import com.mvelproject.dynamicsurveyresponseanalyzer.entities.Survey;
import com.mvelproject.dynamicsurveyresponseanalyzer.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SurveyInitializer implements CommandLineRunner {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyInitializer(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Survey survey = new Survey("1", "Customer Feedback");
        // Add questions and answers
        // surveyRepository.saveSurvey(survey);
    }
}
