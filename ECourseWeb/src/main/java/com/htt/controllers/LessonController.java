/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Lesson;
import com.htt.service.CourseService;
import com.htt.service.LessonService;
import com.htt.service.VideoService;
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
    private VideoService videoSer;

    @Autowired
    private CourseService courseSer;

    @ModelAttribute
    public void commAttrs(Model model) {
        model.addAttribute("courses", this.courseSer.getCourses());
    }

    @GetMapping("/lessons")
    public String viewLessons(Model model) {
        model.addAttribute("lesson", new Lesson());

        return "lesson";
    }

    @GetMapping("/lesson")
    public String viewLesson(Model model) {
        model.addAttribute("lessons", this.lessonSer.getLessons());
        model.addAttribute("lesson", new Lesson());
        return "lesson";
    }

    @GetMapping("/lessonAU")
    public String LessonAUView(Model model) {
        model.addAttribute("lesson", new Lesson());
//        model.addAttribute("lesson", this.lessonSer.getLessons());
        return "lessonAU";
    }

    @PostMapping("/lessonAU")
    public String createView1(Model model, @ModelAttribute(value = "lesson") @Valid Lesson c,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "lessonAU";
        }
        this.lessonSer.addOrUpdate(c);

        return "lesson";
    }

    @GetMapping("/lesson/{lessonId}")
    public String LessonView(Model model, @PathVariable(value = "lessonId") int id) {
        model.addAttribute("lesson", this.lessonSer.getLessonById(id));
        return "lessonAU";
    }

    @PostMapping("/lesson/{lessonId}")
    public String createView(@PathVariable("lessonId") int id,
            @ModelAttribute @Valid Lesson lesson, BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            return "lessonAU";
        }
        this.lessonSer.addOrUpdate(lesson);
        return "lesson";
    }

//    -------------------------------------------------------------------------------------
    @GetMapping("/lessonsManagement")
    public String LessonsManagementView(Model model) {
        model.addAttribute("lessons", this.lessonSer.getLessons());
        return "lessonsManagement";
    }

    @GetMapping("/lessonsManagement/{lessonId}")
    public String LessonIdManagementView(Model model, @PathVariable(value = "lessonId") int id) {
        model.addAttribute("lesson", this.lessonSer.getLessonById(id));

        return "lessonsManagementCreate";
    }

    @GetMapping("/lessonsManagementCreate")
    public String createLessonView(Model model) {
        model.addAttribute("lesson", new Lesson());

        return "lessonsManagementCreate";
    }

    @PostMapping("/lessonsManagementCreate")
    public String createLessonPost(Model model, @ModelAttribute(value = "lesson") @Valid Lesson c,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "lessonsManagementCreate";
        }
        this.lessonSer.addOrUpdate(c);

        return "lessonsManagement";
    }
}
