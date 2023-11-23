package com.practice.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.quiz.entity.QuestionEntity;
import com.practice.quiz.services.QuestionService;

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
}
