/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.service.UserAssignmentDoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiUserDoneController {
    
    @Autowired
    private UserAssignmentDoneService userAssignSer;
    
    @GetMapping("/userdone/assignment/{assignmentId}/user/{userId}")
    public ResponseEntity<?> getUserDoneAssignment(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "assignmentId") Long assignmentId
    ){
        return ResponseEntity.ok(userAssignSer.getAllByUserAndAssignmentId(userId, assignmentId));
    }
}
