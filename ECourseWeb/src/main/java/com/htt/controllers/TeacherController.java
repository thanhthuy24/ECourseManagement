/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Teacher;
import com.htt.pojo.User;
import com.htt.service.TeacherService;
import com.htt.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Admin
 */
@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherSer;

    @Autowired
    private UserService userSer;

    @ModelAttribute
    public void commAttrs(Model model) {
        model.addAttribute("users", userSer.getUsers());
    }

//    xem thông tin của tất cả teachers
    @GetMapping("/teachers")
    public String teacherView(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers";
    } 

//    chỉnh sửa thông tin teacher
    @GetMapping("/teachers/{teacherId}")
    public String teacherView(Model model, @PathVariable(value = "teacherId") Long id) {
        model.addAttribute("teacher", this.teacherSer.getTeacherById(id));
        model.addAttribute("user", this.userSer.getUserById(id));
        return "teacher";
    }
    
    @GetMapping("/teacherCreate")
    public String teacherView2(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher";
    }

    @PostMapping("/teachers")
    public String create(Model model,
            @ModelAttribute(value = "teacher") @Valid Teacher teacher,
            BindingResult userResult) {

        if (userResult.hasErrors()) {
            return "teacher";
        }
        this.teacherSer.addOrUpdate(teacher);
        return "teachers";
    }
}
