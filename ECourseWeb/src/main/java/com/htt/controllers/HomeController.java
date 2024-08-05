/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.service.CategoryService;
import com.htt.service.CourseService;
import com.htt.service.TagService;
import com.htt.service.TeacherService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private CategoryService cateService;
    
    @Autowired
    private TeacherService teacherService;
    
    @Autowired
    private TagService tagService;
    
    @ModelAttribute
    public void commAttrs(Model model) {
        model.addAttribute("categories", cateService.getCates());
        model.addAttribute("teachers", teacherService.getTeachers());
        model.addAttribute("tags", tagService.getTags());
    }
    
    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params){
//        model.addAttribute("cates", this.cateService.getCates());
        model.addAttribute("courses", this.courseService.getCourses(params));
        
        return "home";
    }
}
