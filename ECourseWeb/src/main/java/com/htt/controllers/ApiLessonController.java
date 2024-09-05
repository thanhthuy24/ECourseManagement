/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Lesson;
import com.htt.service.CourseService;
import com.htt.service.LessonService;
import java.util.List;
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
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiLessonController {
    @Autowired
    private LessonService lessonSer;
    
    @DeleteMapping("/lessons/{lessonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "lessonId") Long id){
        this.lessonSer.deleteLesson(id);
    }
    
    @GetMapping("/lessons/{courseId}")
    public ResponseEntity<?> getLessons(
            @PathVariable("courseId") Long courseId
    ){
        try {
            List<Lesson> listLessons = this.lessonSer.getLessonsByCourseId(courseId);
            return ResponseEntity.ok(listLessons);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/lesson")
    public ResponseEntity<?> listLesson(){
        return ResponseEntity.ok(lessonSer.getLessons());
    }
}
