/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.dto.QuestionDTO;
import com.htt.pojo.Question;
import com.htt.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiQuestionController {

    @Autowired
    private QuestionService questionSer;

    @PostMapping("/questions")
    public void addQuestion(
            @RequestBody Question question
    ) {
        this.questionSer.addOrUpdateQuestions(question);
    }
    @PostMapping("/questions/course/{courseId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestion1(
            @RequestBody Question question,
            @PathVariable(value = "courseId") Long courseId
    ) {
        this.questionSer.addOrUpdateQuestions(question);
    }

    @GetMapping("/questions/assignment/{assignmentId}")
    public ResponseEntity<?> listQuestionsByAssignmentId(
            @PathVariable(value = "assignmentId") Long assignmentId
    ) {
        return ResponseEntity.ok(questionSer.getQuestionsByAssignmentId(assignmentId));
    }

}
