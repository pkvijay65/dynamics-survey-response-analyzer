package com.mvelproject.dynamicsurveyresponseanalyzer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private int id;
    private String text;
    private List<Answer> answers;

    // Constructors, Getters and Setters
}
