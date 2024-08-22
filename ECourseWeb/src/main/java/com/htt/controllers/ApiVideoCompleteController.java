/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.dto.UserDTO;
import com.htt.dto.VideoCompleteDTO;
import com.htt.dto.VideoDTO;
import com.htt.pojo.Videocomplete;
import com.htt.service.VideoCompleteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiVideoCompleteController {

    @Autowired
    private VideoCompleteService videoCompleteSer;

//    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addVideoComplete")
    @ResponseStatus(HttpStatus.CREATED)
    public void addVideoComplete(
            @RequestParam Long userId,
            @RequestParam Long videoId
    ) {
        this.videoCompleteSer.addVideos(userId, videoId);
    }

    @GetMapping("/videosCompleted/{userId}")
    public ResponseEntity<?> getVideosCompleteds(
//            @RequestParam Long userId
            @PathVariable("userId") Long userId
    ) {
        try {
            List<Videocomplete> videosCompleted = videoCompleteSer.getVideosCompleted(userId);
            return ResponseEntity.ok(videosCompleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/videosCompleted")
    public ResponseEntity<?> getVideosCompleted(
            @RequestParam Long userId
    ) {
        try {
            List<Videocomplete> videosCompleted = videoCompleteSer.getVideosCompleted(userId);
            return ResponseEntity.ok(videosCompleted);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
