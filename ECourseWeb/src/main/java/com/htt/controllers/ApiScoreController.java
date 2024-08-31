/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Score;
import com.htt.service.ScoreService;
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
public class ApiScoreController {

    @Autowired
    private ScoreService scoreSer;

    @PostMapping("/score/assignment/{assignmentId}/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addScore(
            @PathVariable(value = "assignmentId") Long assignmentId,
            @PathVariable(value = "userId") Long userId
    ) {
        this.scoreSer.addScore(assignmentId, userId);
    }
    
    @PostMapping("/score")
    @ResponseStatus(HttpStatus.CREATED)
    public void addScoreEssay(
            @RequestBody Score sc
    ) {
       if (sc.getAssignmentId() != null && sc.getUserId() != null) {
        this.scoreSer.addScoreEssay(sc, 
                sc.getAssignmentId().getId(), 
                sc.getUserId().getId());
    } else {
        // Handle the case where Assignment or User is null
        throw new IllegalArgumentException("Assignment or User cannot be null");
        }
    }

    @GetMapping("/scores/assignment/{assignmentId}/user/{userId}")
    public ResponseEntity<?> getScore(
            @PathVariable(value = "assignmentId") Long assignmentId,
            @PathVariable(value = "userId") Long userId
    ) {
        return ResponseEntity.ok(this.scoreSer.getScoreByUserIdAndAssignment(assignmentId, userId)); 
    }
}
