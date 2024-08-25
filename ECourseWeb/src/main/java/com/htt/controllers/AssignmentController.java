/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Assignment;
import com.htt.service.AssignmentService;
import com.htt.service.CourseService;
import com.htt.service.LessonService;
import com.htt.service.TagService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Admin
 */
@Controller
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private TagService tagSer;

    @Autowired
    private LessonService lessonSer;
    
    @Autowired
    private CourseService courseSer;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ModelAttribute
    public void commAttrs(Model model) {
        model.addAttribute("tags", this.tagSer.getTags());
        model.addAttribute("lessons", this.lessonSer.getLessons());
        model.addAttribute("courses", this.courseSer.getCourses());
    }

    @GetMapping("/assignments")
    public String listAssignments(
            Model model
    ) {
        model.addAttribute("assignments", this.assignmentService.getAssignments());
        return "assignments";
    }

    @GetMapping("/assignment")
    public String assignmentView(Model model) {
        model.addAttribute("assignment", new Assignment());
        return "assignment";
    }
    
     @GetMapping("/assignments/{assignmentId}")
    public String assignmentById(
            Model model,
            @Valid @PathVariable(value = "assignmentId") Long id) {
        model.addAttribute("assignment", this.assignmentService.getAssignmentById(id));
        return "assignment";
    }

    @PostMapping("/assignments")
    public String createView(Model model,
            @ModelAttribute Assignment assignment,
            BindingResult rs) {
        Date today = new Date();
        if (assignment.getDueDate() == null || assignment.getDueDate().before(today)) {
            rs.rejectValue("dueDate", "error.assignment", "Due date must be today or in the future.");
            return "assignment";
        }

        if (rs.hasErrors()) {
            return "assignment";
        }
        this.assignmentService.addOrUpdate(assignment);
        return "redirect:/";
    }

}
