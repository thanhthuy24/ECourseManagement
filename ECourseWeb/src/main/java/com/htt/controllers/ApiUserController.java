/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.components.JwtService;
import com.htt.dto.EnrollmentDTO;
import com.htt.dto.UserDTO;
import com.htt.dto.UserEnrollDTO;
import com.htt.pojo.Enrollment;
import com.htt.pojo.User;
import com.htt.service.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/users",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<?> addUser(@RequestParam Map<String, String> params, 
            @RequestPart MultipartFile avatar) {
        User user = this.userService.addUser(params, avatar);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping(path = "/current-user", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> details(Principal user) {
        User u = this.userService.getUserByUsername(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    
//    
//    @GetMapping(path = "/my-courses", produces = MediaType.APPLICATION_JSON_VALUE)
//    @CrossOrigin
//    public ResponseEntity<UserEnrollDTO> myCourses(Principal user) {
//        User u = this.userService.getUserByUsername(user.getName());
//        UserEnrollDTO userEnroll = convertToDTO1(u);
//        return new ResponseEntity<>(userEnroll, HttpStatus.OK);
//    }
//    
//    private UserEnrollDTO convertToDTO1(User user, Enrollment enroll) {
//        UserEnrollDTO userEnrollDTO = new UserEnrollDTO();
//        userEnrollDTO.setUsername(user.getUsername());
//        userEnrollDTO.setAvatar(user.getAvatar());
//
//        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
//        
////        enrollmentDTO.setCourse(enroll.getCourseId());
//        enrollmentDTO.setEnrollmentDate(enroll.getEnrollmentDate());
//        
//        
//        userEnrollDTO.setEnroll(enrollmentDTO);
//        
//        return userEnrollDTO;
//    }
    
    
}
