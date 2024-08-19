/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.service;

import com.htt.pojo.Course;
import com.htt.pojo.Enrollment;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    public List<Enrollment> getEnrollmentByUserId(Long id);
//    public List<Course> getCoursesForLoggedInUser(Long userId);
}
