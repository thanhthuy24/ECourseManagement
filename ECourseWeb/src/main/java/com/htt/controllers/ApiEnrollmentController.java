/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.dto.CourseDTO;
import com.htt.dto.EnrollmentDTO;
import com.htt.dto.UserDTO;
import com.htt.dto.UserEnrollDTO;
import com.htt.pojo.Course;
import com.htt.pojo.Enrollment;
import com.htt.pojo.User;
import com.htt.service.CourseService;
import com.htt.service.EnrollmentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/enrollment")
@CrossOrigin
public class ApiEnrollmentController {

    @Autowired
    private EnrollmentService enrollService;

    @GetMapping("")
    public ResponseEntity<List<EnrollmentDTO>> listEnrollment() {
        List<Enrollment> enroll = this.enrollService.getAllEnrollments();
        List<EnrollmentDTO> enrollmentDTOList = convertToDTO(enroll);
        return new ResponseEntity<>(enrollmentDTOList, HttpStatus.OK);
    }
    
//    @GetMapping("/my-courses")
//    public ResponseEntity<UserEnrollDTO> listCoursesByUser() {
//        List<Enrollment> enroll = this.enrollService.getAllEnrollments();
//        List<EnrollmentDTO> enrollmentDTOList = convertToDTO(enroll);
//        return new ResponseEntity<>(enrollmentDTOList, HttpStatus.OK);
//    }

    private List<EnrollmentDTO> convertToDTO(List<Enrollment> enrollments) {
        List<EnrollmentDTO> enrollmentDTOList = new ArrayList<>();
        for (Enrollment t : enrollments) {
            EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
            enrollmentDTO.setEnrollmentDate(t.getEnrollmentDate());

            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setDescription(t.getCourseId().getDescription());
            courseDTO.setImage(t.getCourseId().getImage());
            courseDTO.setName(t.getCourseId().getName());
            enrollmentDTO.setCourse(courseDTO);

            UserDTO userDTO = new UserDTO();
            userDTO.setId(t.getUserId().getId());
            userDTO.setUsername(t.getUserId().getUsername());
            userDTO.setAvatar(t.getUserId().getAvatar());
            userDTO.setEmail(t.getUserId().getEmail());
            userDTO.setPhoneNumber(t.getUserId().getPhoneNumber());
//            enrollmentDTO.setUser(userDTO);
            
            enrollmentDTOList.add(enrollmentDTO);
        }

        return enrollmentDTOList;
    }

}
