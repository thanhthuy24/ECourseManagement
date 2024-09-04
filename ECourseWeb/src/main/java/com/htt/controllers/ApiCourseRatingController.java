/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Courserating;
import com.htt.service.CourseRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiCourseRatingController {

    @Autowired
    private CourseRatingService courseRatingSer;

    @PostMapping("/courseRating/course/{courseId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void estimateCourse(
            @RequestBody Courserating c,
            @PathVariable Long courseId
    ) {
        this.courseRatingSer.addRating(c, courseId);
    }

    @GetMapping("/courseRating/user/{userId}/course/{courseId}")
    public ResponseEntity<?> checkRating(
            @PathVariable Long userId,
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(this.courseRatingSer.checkCourseRating(userId, courseId));
    }

    @GetMapping("/courseRating/avgCourse/{courseId}")
    public ResponseEntity<?> calculateRating(
            @PathVariable Long courseId
    ) {
        float avg = courseRatingSer.calculateCourseRating(courseId);
        return ResponseEntity.ok(avg);
    }

    @GetMapping("/courseRating/course/{courseId}/rating/{rating}")
    public ResponseEntity<?> countRating(
            @PathVariable Long courseId,
            @PathVariable Long rating
    ) {
        float count = courseRatingSer.countRatinngByCourse(rating, courseId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/courseRating/count/course/{courseId}")
    public ResponseEntity<?> countRatingInCourse(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(this.courseRatingSer.countRating(courseId));
    }
    
    @GetMapping("/courseRating/listRating/course/{courseId}")
    public ResponseEntity<?> getListRating(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(this.courseRatingSer.getCourseRatingByCourseId(courseId));
    }

}
