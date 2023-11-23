package com.practice.quiz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.quiz.repository.QuestionRepository;
import com.practice.quiz.entity.QuestionEntity;

@Service
public class QuestionService {
         //@Autowired(required = true) ---->  field injection
       private final QuestionRepository questionRepository;
   
    @Autowired(required = true) // constructor injection
    QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<List<QuestionEntity>> fetchAllQuestions(){
        return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);

    }

    public List<QuestionEntity> fetchQuestionsByCategory(String categoryName){
        return questionRepository.findByCategory(categoryName);
    }

    public String addQuestion(QuestionEntity question){

        questionRepository.save(question);

        return "Success";
    }



    
}
