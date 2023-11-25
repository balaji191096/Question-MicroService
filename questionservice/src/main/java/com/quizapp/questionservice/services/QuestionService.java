package com.quizapp.questionservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.questionservice.entity.QuestionEntity;
import com.quizapp.questionservice.model.QuestionWrapper;
import com.quizapp.questionservice.model.ResponseModel;
import com.quizapp.questionservice.repository.QuestionRepository;



@Service
public class QuestionService {
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

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String categoryName, Integer num){

        List<Integer> questionIds = questionRepository.findRandomQuestionByCategory(categoryName, num);

        return new ResponseEntity<>(questionIds, HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> fetchQuestions(List<Long> questionIds){

        List<QuestionWrapper> wrapper = this.questionRepository.findQuestionsByIds(questionIds);

        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(List<ResponseModel> responses){

        int right = 0;
        for(ResponseModel r: responses){
            
          QuestionEntity question = this.questionRepository.findById(r.getId()).get(); 
      
          if(r.getAnswer().equals(question.getRightAnswer()))
            right++;
          
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
