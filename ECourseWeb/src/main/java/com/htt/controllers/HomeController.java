/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.service.CategoryService;
import com.htt.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @RequestMapping("/")
    
    public String index(Model model){
        model.addAttribute("cates", this.cateService.getCates());
        model.addAttribute("courses", this.courseService.getCourses(null));
        
        return "index";
    }
}
