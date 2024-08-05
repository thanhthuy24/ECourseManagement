/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Course;
import com.htt.pojo.Teacher;
import com.htt.service.CourseService;
import com.htt.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Admin
 */
@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherSer;
    
    @GetMapping("/teachers")
    public String viewTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers";
    }
}
