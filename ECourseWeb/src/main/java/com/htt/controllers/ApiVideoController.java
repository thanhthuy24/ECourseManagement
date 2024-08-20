/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.pojo.Video;
import com.htt.service.VideoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiVideoController {
    @Autowired
    private VideoService videoSer;
    
    @DeleteMapping("/videos/{videoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "videoId") Long id){
        this.videoSer.deleteVideo(id);
    }
    
    @GetMapping("/videos/{lessonId}")
    public ResponseEntity<?> getVideosByLessonId(
            @PathVariable("lessonId") Long lessonId
    ){
        try {
            List<Video> listVideos = this.videoSer.getVideoByLessonId(lessonId);
            return ResponseEntity.ok(listVideos);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/videos")
    public ResponseEntity<?> getVideos(){
        try {
            List<Video> listVideos = this.videoSer.getVideos();
            return ResponseEntity.ok(listVideos);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
