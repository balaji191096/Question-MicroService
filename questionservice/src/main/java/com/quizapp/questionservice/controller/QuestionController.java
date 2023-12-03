package com.quizapp.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.questionservice.entity.QuestionEntity;
import com.quizapp.questionservice.model.QuestionWrapper;
import com.quizapp.questionservice.model.ResponseModel;
import com.quizapp.questionservice.services.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }


    @GetMapping("allquestions")
    public ResponseEntity<List<QuestionEntity>> fetchAllQuestions(){
        return this.questionService.fetchAllQuestions();
    }


    @GetMapping("category/{categoryName}")
    public List<QuestionEntity> fetchQuestionsByCategory(@PathVariable String categoryName){
        return this.questionService.fetchQuestionsByCategory(categoryName);
    }

    @PostMapping("add")
    public String addQuestion(@RequestBody QuestionEntity question){
        return this.questionService.addQuestion((question));
    }

//  generate questions
// get questions by question Id
//  calculate result
    @PostMapping("generate")
    public ResponseEntity<List<Long>> generateQuestionForQuiz(@RequestParam String categoryName, @RequestParam Integer num){
        return this.questionService.generateQuestionsForQuiz(categoryName, num);
    }

    @PostMapping("get-questions-for-quiz")
    public ResponseEntity<List<QuestionWrapper>> fetchQuestionsByIds(@RequestBody List<Long> questionIds ){
        return this.questionService.fetchQuestions(questionIds);
    }

    @PostMapping("getscore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<ResponseModel> responses){
        return this.questionService.calculateScore(responses);
    }

}
