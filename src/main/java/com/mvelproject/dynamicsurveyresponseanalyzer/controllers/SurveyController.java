package com.mvelproject.dynamicsurveyresponseanalyzer.controllers;

import com.mvelproject.dynamicsurveyresponseanalyzer.entities.Answer;
import com.mvelproject.dynamicsurveyresponseanalyzer.entities.Question;
import com.mvelproject.dynamicsurveyresponseanalyzer.entities.Rule;
import com.mvelproject.dynamicsurveyresponseanalyzer.entities.Survey;
import com.mvelproject.dynamicsurveyresponseanalyzer.repositories.SurveyRepository;
import com.mvelproject.dynamicsurveyresponseanalyzer.services.RuleService;
import org.mvel2.MVEL;
import org.mvel2.compiler.ExecutableStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.*;


@Controller
public class SurveyController {

    private static String storedMvelRule;
    @GetMapping("/")
    public String index() {
        return "index";
    }



    @GetMapping("/survey")
    public String showSurvey(Model model) {
        // Create a mock survey
        Survey survey = createMockSurvey();
        model.addAttribute("survey", survey);
        return "survey";
    }
    @PostMapping("/submitSurvey")
    public String submitSurvey(@RequestParam Map<String, String> allParams, Model model) {
        try {
            String mvelRule = "int score = 0; " +
                    "if (allParams.get('question_1').equals('Very Satisfied')) score += 10; " +
                    "if (allParams.get('question_2').equals('Very Likely')) score += 5; " +
                    "if (allParams.get('question_3').equals('Excellent')) score += 10; " +
                    "return score > 20 ? 'Highly Satisfied Customer' : 'Needs Improvement';";
//            String mvelRule = (storedMvelRule != null) ? storedMvelRule :
//                    "int positiveCount = 0; int totalCount = 0; " +
//                            "for (entry : allParams.entrySet()) {" +
//                            "  if (entry.getValue().equals('Very Satisfied') || entry.getValue().equals('Yes')) positiveCount++;" +
//                            "  totalCount++;" +
//                            "}" +
//                            "double positiveRatio = positiveCount / (double) totalCount;" +
//                            "return positiveRatio > 0.5 ? 'Overall Positive' : 'Needs Improvement';";

            Serializable compiledRule = MVEL.compileExpression(mvelRule);
            Map<String, Object> vars = new HashMap<>();
            vars.put("allParams", new HashMap<>(allParams));

            String feedbackCategory = (String) MVEL.executeExpression(compiledRule, vars);
            model.addAttribute("feedbackCategory", feedbackCategory);
            return "surveyResult";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred: " + e.getMessage());
            return "errorPage";
        }
    }

    @PostMapping("/addRule")
    public String addRule(@RequestParam String mvelRule, Model model) {
        storedMvelRule = mvelRule;
        model.addAttribute("mvelRule", mvelRule);
        return "ruleAdded"; // Redirect to a confirmation page
    }




    private Survey createMockSurvey() {
        // Create a sample survey for demonstration
        Survey survey = new Survey();
        survey.setTitle("Sample Survey");

        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setId(1);
        question1.setText("How satisfied are you with our product?");
        Answer answer1 = new Answer(1, "Very Satisfied");
        Answer answer2 = new Answer(2, "Satisfied");
        Answer answer3 = new Answer(3, "Neutral");
        Answer answer4 = new Answer(4, "Unsatisfied");
        Answer answer5 = new Answer(5, "Very Unsatisfied");
        question1.setAnswers(List.of(answer1, answer2, answer3, answer4, answer5));
        questions.add(question1);

        Question question2 = new Question();
        question2.setId(2);
        question2.setText("Do you recommend our product?");
        question2.setAnswers(List.of(
                new Answer(1, "Definitely"),
                new Answer(2, "Maybe"),
                new Answer(3, "Not sure"),
                new Answer(4, "No")
        ));
        questions.add(question2);

        survey.setQuestions(questions);

        // Example: Yes/No question
        Question yesNoQuestion = new Question();
        yesNoQuestion.setId(2);
        yesNoQuestion.setText("Would you use our product again?");
        yesNoQuestion.setAnswers(List.of(new Answer(1, "Yes"), new Answer(2, "No")));
        questions.add(yesNoQuestion);

        // Example: Rating question
        Question ratingQuestion = new Question();
        ratingQuestion.setId(3);
        ratingQuestion.setText("Rate our customer service (1-5)");
        ratingQuestion.setAnswers(List.of(
                new Answer(1, "1"),
                new Answer(2, "2"),
                new Answer(3, "3"),
                new Answer(4, "4"),
                new Answer(5, "5")
        ));
        questions.add(ratingQuestion);


        survey.setQuestions(questions);
        return survey;
    }

}
