/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiAssignmentController {
    @Autowired
    private AssignmentService assignmentSer;
    
    @DeleteMapping("/assignments/{assignmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "assignmentId") Long id){
        this.assignmentSer.deleteAssignment(id);
    }
    
//    @CrossOrigin("http://localhost:3000")
    @GetMapping("/lecturer/assignments/courses/{courseId}")
    public ResponseEntity<?> getAssignmentsByCourse(
            @PathVariable(value = "courseId") Long courseId
    ){
        return ResponseEntity.ok(assignmentSer.getAssignmentByCourseId(courseId));
    }
    
}
