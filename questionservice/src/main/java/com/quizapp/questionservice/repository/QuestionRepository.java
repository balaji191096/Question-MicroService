package com.practice.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practice.quiz.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findByCategory(String category);

    @Query(value = "SELECT * FROM tbl_question WHERE category =:category ORDER BY RANDOM() LIMIT :num", nativeQuery = true)
    List<QuestionEntity> findRandomQuestionByCategory(String category, int num);

} 