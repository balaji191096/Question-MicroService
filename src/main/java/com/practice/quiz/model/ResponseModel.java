package com.practice.quiz.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseModel {

    private Long questionId;
    private String answer;
    
}
