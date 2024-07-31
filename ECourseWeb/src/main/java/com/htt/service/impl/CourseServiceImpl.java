/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Course;
import com.htt.repository.CourseRepository;
import com.htt.service.CourseService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CourseServiceImpl implements CourseService{
    
    @Autowired
    private CourseRepository courseRepo;

    @Override
    public List<Course> getCourses(Map<String, String> params) {
        return this.courseRepo.getCourses(params);
    }

    @Override
    public void addOrUpdate(Course c) {
        this.courseRepo.addOrUpdate(c);
    }

    @Override
    public Course getCourseById(int id) {
        return this.courseRepo.getCourseById(id);
    }

    @Override
    public void deleteCourse(int id) {
        this.courseRepo.deleteCourse(id);
    }
    
}
