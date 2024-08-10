/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Lesson;
import com.htt.service.CourseService;
import com.htt.service.LessonService;
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
public class LessonController {

    @Autowired
    private LessonService lessonSer;

    @Autowired
    private CourseService courseSer;

    @ModelAttribute
    public void commAttrs(Model model) {
        model.addAttribute("courses", this.courseSer.getCourses());
    }

    @GetMapping("/lessons")
    public String viewLessons(Model model) {
        model.addAttribute("lessons", this.lessonSer.getLessons());

        return "lessons";
    }

    @GetMapping("/lesson")
    public String lessonCreate(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "lesson";
    }

    @GetMapping("/lessons/{lessonId}")
    public String LessonView(Model model, @PathVariable(value = "lessonId") int id) {
        model.addAttribute("lesson", this.lessonSer.getLessonById(id));
        return "lesson";
    }

    @PostMapping("/lessons")
    public String createView(Model model, @ModelAttribute Lesson lesson,
            BindingResult rs) {
        if (rs.hasErrors()) {
            
            return "lesson";
        }
        this.lessonSer.addOrUpdate(lesson);
        return "lessons";
    }
}
