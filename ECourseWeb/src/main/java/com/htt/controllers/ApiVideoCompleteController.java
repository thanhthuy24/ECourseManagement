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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class ApiVideoCompleteController {

    @Autowired
    private VideoCompleteService videoCompleteSer;
    
    @PostMapping("/addVideoComplete")
    @ResponseStatus(HttpStatus.CREATED)
    public void pay(@RequestBody Videocomplete videoComplete) {
        this.videoCompleteSer.addVideos(videoComplete);
    }

//    @PostMapping("/addVideo")
//    public ResponseEntity<?> addVideo(
//            @ModelAttribute Videocomplete videoComplete,
//            BindingResult rs
//    ) {
//        Videocomplete video = this.videoCompleteSer.addVideos(videoComplete);
//        VideoCompleteDTO videoCompleteDTO = convertToDTO(video);
//        return ResponseEntity.ok(videoCompleteDTO);
//
//    }
//    public ResponseEntity<?> addVideo(
//            @ModelAttribute Videocomplete videoComplete,
//            BindingResult rs
//    ) {
//        // Kiểm tra BindingResult trước khi thực hiện thao tác
//        if (rs.hasErrors()) {
//            return ResponseEntity.badRequest().body(rs.getAllErrors());
//        }
//
//        // Đảm bảo videoComplete có userId và videoId hợp lệ
//        if (videoComplete.getUserId() == null || videoComplete.getVideoId() == null) {
//            return ResponseEntity.badRequest().body("User or Video cannot be null");
//        }
//
//        Videocomplete video = this.videoCompleteSer.addVideos(videoComplete);
//        VideoCompleteDTO videoCompleteDTO = convertToDTO(video);
//        return ResponseEntity.ok(videoCompleteDTO);
//    }
//
////    private VideoCompleteDTO convertToDTO(Videocomplete t) {
////        VideoCompleteDTO videoCompleteDTO = new VideoCompleteDTO();
////        videoCompleteDTO.setCompletedDate(t.getCompletedDate());
////
////        UserDTO userDTO = new UserDTO();
////        userDTO.setId(t.getUserId().getId());
////        
////        VideoDTO videoDTO = new VideoDTO();
////        videoDTO.setId(t.getVideoId().getId());
////
////        videoCompleteDTO.setUser(userDTO);
////        videoCompleteDTO.setVideo(videoDTO);
////
////        return videoCompleteDTO;
////    }
//    private VideoCompleteDTO convertToDTO(Videocomplete t) {
//        VideoCompleteDTO videoCompleteDTO = new VideoCompleteDTO();
//        videoCompleteDTO.setCompletedDate(t.getCompletedDate());
//
//        // Kiểm tra UserId có phải là null không
//        if (t.getUserId() != null) {
//            UserDTO userDTO = new UserDTO();
//            userDTO.setId(t.getUserId().getId());
//            videoCompleteDTO.setUser(userDTO);
//        } else {
//            // Xử lý khi UserId là null, nếu cần
//            videoCompleteDTO.setUser(null);
//        }
//
//        // Kiểm tra VideoId có phải là null không
//        if (t.getVideoId() != null) {
//            VideoDTO videoDTO = new VideoDTO();
//            videoDTO.setId(t.getVideoId().getId());
//            videoCompleteDTO.setVideo(videoDTO);
//        } else {
//            // Xử lý khi VideoId là null, nếu cần
//            videoCompleteDTO.setVideo(null);
//        }
//
//        return videoCompleteDTO;
//    }

}
