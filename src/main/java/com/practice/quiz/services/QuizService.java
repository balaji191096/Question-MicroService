package com.practice.quiz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.practice.quiz.entity.QuestionEntity;
import com.practice.quiz.entity.QuizEntity;
import com.practice.quiz.model.QuestionWrapper;
import com.practice.quiz.model.ResponseModel;
import com.practice.quiz.repository.QuestionRepository;
import com.practice.quiz.repository.QuizRepository;


@Service
public class QuizService {
    @Autowired
      QuestionRepository questionRepository;
    @Autowired
      QuizRepository quizRepository;



    public ResponseEntity<String> addQuiz(String category, int num, String title) {
        
        List<QuestionEntity> questions = questionRepository.findRandomQuestionByCategory(category, num);

        QuizEntity  quiz = new QuizEntity();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> fetchQuestionsByQuizId(Long quizId){

        Optional<QuizEntity> quiz = quizRepository.findById(quizId);

        List<QuestionEntity> quetionsFromDB = quiz.isPresent() ? quiz.get().getQuestions() : new ArrayList<>();

        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for(QuestionEntity q : quetionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());

            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);        

    }

    public ResponseEntity<Integer> calculateResult(Long quizId,  List<ResponseModel> responses){

        Optional<QuizEntity> quiz = quizRepository.findById(quizId);

        List<QuestionEntity> quetionsFromDB = quiz.isPresent() ? quiz.get().getQuestions() : new ArrayList<>();

        int right = 0;
        int i = 0;
        
        for(ResponseModel r: responses){
            if(r.getAnswer().equals(quetionsFromDB.get(i).getRightAnswer()))
                right++;
        
            i++;
        }
        
        return new ResponseEntity<>(right, HttpStatus.OK);

    }

    



    
}
