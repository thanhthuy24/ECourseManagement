/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Answerchoice;
import com.htt.service.AnswerchoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ApiAnswerChoiceController {
    @Autowired
    private AnswerchoiceService answerChoiceSer;
    
    @PostMapping("/answerchoices")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAnswerChoice(
            @RequestBody Answerchoice ans           
    ){
        
        this.answerChoiceSer.addAnswerChoice(ans,
                ans.getAssignmentId().getId(), ans.getUserId().getId(), ans.getQuestionId().getId());
    }
    
    @GetMapping("/answerchoices/user/{userId}")
    public ResponseEntity<?> getAnswerByUserId(
            @PathVariable(value = "userId") Long userId
    ){
        return ResponseEntity.ok(answerChoiceSer.getAnswerchoiceByUserId(userId));
    }
    
    @GetMapping("/answerchoices/user/{userId}/assignment/{assignmentId}")
    public ResponseEntity<?> checkAnswers(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "assignmentId") Long assignmentId
    ){
        return ResponseEntity.ok(answerChoiceSer.getAnswerchoices(userId, assignmentId));
    }
    
}
