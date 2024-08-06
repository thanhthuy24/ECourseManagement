/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Course;
import com.htt.service.CourseService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Admin
 */
@Controller
public class CourseController {

    @Autowired
    private CourseService courseSer;

    @GetMapping("/courses")
    public String viewCourse(Model model) {
        model.addAttribute("course", new Course());
        return "courses";
    }

    @PostMapping("/courses")
    public String createView(Model model, @ModelAttribute(value = "course") @Valid Course c,
            BindingResult rs) {
        if(rs.hasErrors()){
            return "courses";
        }
        this.courseSer.addOrUpdate(c);

        return "redirect:/";
    }
}
