/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Teacher;
import com.htt.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Admin
 */
@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherSer;
    
//    xem thông tin của tất cả teachers
    @GetMapping("/teachers")
    public String viewTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachersView";
    }
    
//    chỉnh sửa thông tin teacher
    @GetMapping("/teachers/{teacherId}")
    public String teacherView(Model model, @PathVariable(value = "teacherId") int id) {
        model.addAttribute("teacher", this.teacherSer.getTeacherById(id));
        return "teachers";
    }
}
