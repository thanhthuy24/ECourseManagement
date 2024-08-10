/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Video;
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
public class VideoController {

    @Autowired
    private VideoService videoSer;

    @Autowired
    private LessonService lessonSer;

    @Autowired
    private CourseService courseSer;

    @ModelAttribute
    public void commAttrs(Model model) {
        model.addAttribute("lessons", this.lessonSer.getLessons());
        model.addAttribute("courses", this.courseSer.getCourses());
    }

    @GetMapping("/videos")
    public String videoView(Model model) {
        model.addAttribute("videos", this.videoSer.getVideos());
        return "videos";
    }

    @GetMapping("/videos/{videoId}")
    public String videoUpdate(Model model, @PathVariable(value = "videoId") int id) {
        model.addAttribute("video", this.videoSer.getVideoById(id));
        return "video";
    }

    @PostMapping("/videos")
    public String createVideo2(Model model, @ModelAttribute(value = "video") @Valid Video c,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "video";
        }
        this.videoSer.addOrUpdate(c);

        return "videos";
    }

    @PostMapping("/videos/{videoId}")
    public String updateVideo(@PathVariable("videoId") int id, 
            @ModelAttribute @Valid Video video, 
            BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            return "video";
        }
        this.videoSer.addOrUpdate(video);
        return "redirect:/videos";
    }
    
    @GetMapping("/videosCreate")
    public String createVideoView(Model model) {
        model.addAttribute("video", new Video());
        return "video";
    }

    @PostMapping("/videosCreate")
    public String createVideo(Model model, @ModelAttribute(value = "video") @Valid Video c,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "video";
        }
        this.videoSer.addOrUpdate(c);

        return "videos";
    }
}
