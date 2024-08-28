/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Choice;
import com.htt.service.ChoiceService;
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
public class ApiChoiceController {
    @Autowired
    private ChoiceService choiceSer;
    
    @PostMapping("/choices")
    @ResponseStatus(HttpStatus.CREATED)
    public void addChoice(
            @RequestBody Choice choice
    ){
        this.choiceSer.addChoice(choice);
    }
    
    @GetMapping("/choices/question/{questionId}")
    public ResponseEntity<?> getListChoiceByQuestionId(
            @PathVariable(value = "questionId") Long questionId
    ){
        return ResponseEntity.ok(choiceSer.getChoicesByQuestionId(questionId));
    }
    
    @GetMapping("/choices/isCorrect/question/{questionId}")
    public ResponseEntity<?> getListRightChoiceByAssignmentId(
            @PathVariable(value = "questionId") Long questionId
    ){
        return ResponseEntity.ok(choiceSer.getChoicesByCorrectByQuestionId(questionId));
    }
    
    
}
