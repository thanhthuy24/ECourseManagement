/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Courserating;
import com.htt.repository.CourseRatingRepository;
import com.htt.service.CourseRatingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CourseRatingServiceImpl implements CourseRatingService{
    
    @Autowired
    private CourseRatingRepository courseRatingRepo;

    @Override
    public void addRating(Courserating c, Long courseId) {
        this.courseRatingRepo.addRating(c, courseId);
    }

    @Override
    public List<Courserating> getCourseRatingByUserId(Long userId) {
        return this.courseRatingRepo.getCourseRatingByUserId(userId);
    }

    @Override
    public List<Courserating> getCourseRatingByCourseId(Long courseId) {
        return this.courseRatingRepo.getCourseRatingByCourseId(courseId);
        
    }

    @Override
    public List<Courserating> checkCourseRating(Long userId, Long courseId) {
        return this.courseRatingRepo.checkCourseRating(userId, courseId);
    }
    
    @Override
    public float calculateCourseRating(Long courseId) {
        return this.courseRatingRepo.calculateCourseRating(courseId);
    }

    @Override
    public float countRatinngByCourse(Long rating, Long courseId) {
        return this.courseRatingRepo.countRatinngByCourse(rating, courseId);
    }

    @Override
    public Long countRating(Long courseId) {
        return this.courseRatingRepo.countRating(courseId);
    }
}
