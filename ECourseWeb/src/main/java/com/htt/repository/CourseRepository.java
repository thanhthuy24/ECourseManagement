/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Course;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface CourseRepository {
    List<Course> getCourses(Map<String, String> params);
    void addOrUpdate(Course c);
    Course getCourseById(int id);
    void deleteCourse(int id);
}
