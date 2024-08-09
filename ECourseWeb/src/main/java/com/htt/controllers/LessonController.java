/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Lesson;
import com.htt.pojo.Video;
import com.htt.repository.VideoRepository;
import com.htt.service.AssignmentService;
import com.htt.service.DocumentService;
import com.htt.service.LessonService;
import com.htt.service.VideoService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class LessonController {

    @Autowired
    private LessonService lessonSer;


    @GetMapping("/lessons")
    public String viewLessons(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "lessons";
    }
    
    @PostMapping("/lesson")
    public String createView(Model model, @ModelAttribute(value = "lesson") @Valid Lesson c,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "lesson";
        }
        this.lessonSer.addOrUpdate(c);

        return "lessons";
    }

}
