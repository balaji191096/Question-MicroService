package com.practice.quiz.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tbl_question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String questionTitle;

    @Column()
    private String option1;

    @Column()
    private String option2;

    @Column()
    private String option3;
    
    @Column()
    private String option4;

    @Column()
    private String rightAnswer;

    @Column()
    private String difficultyLevel;

    @Column()
    private String category;

    @CreationTimestamp()
    private Date createdAt;

    @UpdateTimestamp()
    private Date updatedAt;
    
}
