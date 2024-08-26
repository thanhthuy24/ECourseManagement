/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.dto.AssignmentDTO;
import com.htt.dto.QuestionDTO;
import com.htt.dto.TagDTO;
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
    @ResponseStatus(HttpStatus.CREATED)
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
    
    @PostMapping("/questions/assignments/{assignmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addQuestion2(
            @RequestBody Question question,
            @PathVariable(value = "assignmentId") Long assignmentId
    ) {
        this.questionSer.addOrUpdateQuestions(question);
    }

    @GetMapping("/questions/assignment/{assignmentId}")
    public ResponseEntity<?> listQuestionsByAssignmentId(
            @PathVariable(value = "assignmentId") Long assignmentId
    ) {
        return ResponseEntity.ok(questionSer.getQuestionsByAssignmentId(assignmentId));
    }
    
    @GetMapping("/questions/{questionId}")
    public ResponseEntity<?> getQuestionById(
            @PathVariable(value = "questionId") Long questionId
    ){
        Question question = this.questionSer.getQuestionById(questionId);
        QuestionDTO questionDTO = convertToDTO(question);
        
        return ResponseEntity.ok(questionDTO);
    }
    
    private QuestionDTO convertToDTO(Question t) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(t.getId());
        questionDTO.setName(t.getName());
        
        TagDTO tagDTO = new TagDTO();
        tagDTO.setName(t.getTagId().getName());
        
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setName(t.getAssignmentId().getName());
        
        questionDTO.setTag(tagDTO);
        questionDTO.setAssignment(assignmentDTO);

        return questionDTO;
    }

}
