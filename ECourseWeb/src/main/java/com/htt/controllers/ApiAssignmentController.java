/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.dto.AssignmentDTO;
import com.htt.dto.CourseDTO;
import com.htt.dto.LessonDTO;
import com.htt.dto.TagDTO;
import com.htt.pojo.Assignment;
import com.htt.service.AssignmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
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
public class ApiAssignmentController {
    @Autowired
    private AssignmentService assignmentSer;
    
    @DeleteMapping("/assignment/{assignmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "assignmentId") Long id){
        this.assignmentSer.deleteAssignment(id);
    }
    
    @CrossOrigin("http://localhost:3000")
    @GetMapping("/lecturer/assignments/courses/{courseId}")
    public ResponseEntity<?> getAssignmentsByCourse(
            @PathVariable(value = "courseId") Long courseId
    ){
        return ResponseEntity.ok(assignmentSer.getAssignmentByCourseId(courseId));
    }
    
    @GetMapping("/lecturer/assignment/{assignmentId}")
    public ResponseEntity<?> getAssignmentsById(
            @PathVariable(value = "assignmentId") Long assignmentId
    ){
        Assignment assignment = this.assignmentSer.getAssignmentById(assignmentId);
        AssignmentDTO assignmentDTO = convertToDTO(assignment);
        return ResponseEntity.ok(assignmentDTO);
    }
    
    @PostMapping("/lecturer/assignment/{assignmentId}")
    public void updateAssignment(
            @ModelAttribute Assignment assignment
    ) {
        this.assignmentSer.addOrUpdate(assignment);
    }
    
    private AssignmentDTO convertToDTO(Assignment t) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setName(t.getName());
        assignmentDTO.setCreatedDate(t.getCreatedDate());
        assignmentDTO.setDueDate(t.getDueDate());
        
        TagDTO tagDTO = new TagDTO();
        tagDTO.setName(t.getTagId().getName());
        
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setName(t.getLessonId().getName());
        
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(t.getCourseId().getName());
        
        assignmentDTO.setTag(tagDTO);
        assignmentDTO.setLesson(lessonDTO);
        assignmentDTO.setCourse(courseDTO);

        return assignmentDTO;
    }
    
//    Hiển thị ở học viên
    @GetMapping("/assignments/courses/{courseId}")
    public ResponseEntity<?> getAssignmentsByCourseAtUser(
            @PathVariable(value = "courseId") Long courseId
    ){
        return ResponseEntity.ok(assignmentSer.getAssignmentByCourseId(courseId));
    }
    
    
}
