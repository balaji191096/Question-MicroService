package com.practice.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.quiz.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    
} 