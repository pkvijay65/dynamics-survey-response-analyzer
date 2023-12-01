package com.mvelproject.dynamicsurveyresponseanalyzer;

import com.mvelproject.dynamicsurveyresponseanalyzer.entities.Survey;
import com.mvelproject.dynamicsurveyresponseanalyzer.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DynamicSurveyResponseAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicSurveyResponseAnalyzerApplication.class, args);
    }

}

