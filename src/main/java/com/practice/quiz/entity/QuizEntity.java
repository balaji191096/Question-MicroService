package com.practice.quiz.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity(name = "tbl_quiz")
public class QuizEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String title;

    @ManyToMany
    @JoinTable(name = "tbl_questions_quiz")
    private List<QuestionEntity> questions;


    @CreationTimestamp()
    private Timestamp createdAt;

    @UpdateTimestamp()
    private Timestamp updatedAt;


}
