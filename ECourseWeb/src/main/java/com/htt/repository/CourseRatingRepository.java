/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Courserating;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CourseRatingRepository {
    
    float calculateCourseRating(Long courseId);

    void addRating(Courserating c, Long courseId);
    
    Courserating checkCourseRating(Long userId, Long courseId);

    List<Courserating> getCourseRatingByUserId(Long userId);

    List<Courserating> getCourseRatingByCourseId(Long courseId);
}
