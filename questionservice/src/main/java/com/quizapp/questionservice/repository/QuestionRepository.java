package com.quizapp.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quizapp.questionservice.entity.QuestionEntity;
import com.quizapp.questionservice.model.QuestionWrapper;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findByCategory(String category);

    @Query(value = "SELECT q.id FROM tbl_question q WHERE category =:category ORDER BY RANDOM() LIMIT :num", nativeQuery = true)
    List<Integer> findRandomQuestionByCategory(String category, int num);

     @Query("SELECT new com.quizapp.questionservice.model.QuestionWrapper(q.id, q.questionTitle, q.option1, q.option2, q.option3, q.option4) FROM tbl_question q WHERE q.id IN :ids")
    List<QuestionWrapper> findQuestionsByIds(@Param("ids") List<Long> ids);


} 