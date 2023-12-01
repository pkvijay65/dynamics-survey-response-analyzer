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
public class Survey {
    private String id;
    private String title;
    private List<Question> questions;

    // Constructors, Getters and Setters
    //create constructor with id and title
    public Survey(String id, String title) {
        this.id = id;
        this.title = title;
    }
}