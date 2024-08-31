/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Essay;
import com.htt.service.EssayService;
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
public class ApiEssayController {
    
    @Autowired
    private EssayService essaySer;
    
    @PostMapping("/essays")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEssay(
            @RequestBody Essay essay
    ){
        this.essaySer.addEssay(essay, 
                essay.getAssignmentId().getId(), essay.getUserId().getId(), essay.getQuestionId().getId());
    }
    
    @GetMapping("/essays/assignment/{assignmentId}")
    public ResponseEntity<?> getEssayAssignmentId(
            @PathVariable(value = "assignmentId") Long assignmentId
    ){
        return ResponseEntity.ok(essaySer.getEssayByAssignmentId(assignmentId));
    }
    
    @GetMapping("/essays/user/{userId}")
    public ResponseEntity<?> getEssayUserId(
            @PathVariable(value = "userId") Long userId
    ){
        return ResponseEntity.ok(essaySer.getEssayByUserId(userId));
    }
    
    @GetMapping("/essays/question/{questionId}")
    public ResponseEntity<?> getEssayQuestionId(
            @PathVariable(value = "questionId") Long questionId
    ){
        return ResponseEntity.ok(essaySer.getEssayByQuestionId(questionId));
    }
    
}
