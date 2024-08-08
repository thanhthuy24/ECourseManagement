/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Course;
import com.htt.pojo.Video;
import com.htt.repository.VideoRepository;
import com.htt.service.VideoService;
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
public class VideoController {
    
    @Autowired
    private VideoService videoSer;
    
    @GetMapping("/videos")
    public String viewCourse(Model model) {
        model.addAttribute("video", new Course());
        return "videos";
    }
    
    @PostMapping("/videos")
    public String createVideo(Model model, @ModelAttribute(value = "video") @Valid Video c,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "videos";
        }
        this.videoSer.addOrUpdate(c);

        return "redirect:/";
    }
    
}
