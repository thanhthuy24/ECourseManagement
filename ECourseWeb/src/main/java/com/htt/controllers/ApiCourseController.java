/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.dto.CourseDTO;
import com.htt.dto.TagDTO;
import com.htt.dto.TeacherDTO;
import com.htt.dto.UserDTO;
import com.htt.pojo.Course;
import com.htt.service.CourseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiCourseController {

    @Autowired
    private CourseService courseSer;

    @DeleteMapping("/courses/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "courseId") Long id) {
        this.courseSer.deleteCourse(id);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> list(@RequestParam Map<String, String> params) {
        List<Course> courses = this.courseSer.getCourses(params);
        List<CourseDTO> coursesDTO = convertToDTO(courses);
        return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseDTO> course(@PathVariable(value = "courseId") Long id) {
        Course courses = this.courseSer.getCourseById(id);
        CourseDTO courseDTO = convertToDTO(courses);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @GetMapping("/courses/teacher/{teacherId}")
    public ResponseEntity<?> listCourseByTeacher(
            @PathVariable(value = "teacherId") Long id
    ) {
        return ResponseEntity.ok(courseSer.getCoursesByTeacherId(id));
    }

    private CourseDTO convertToDTO(Course t) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(t.getId());
        courseDTO.setCreatedDate(t.getCreatedDate());
        courseDTO.setDescription(t.getDescription());
        courseDTO.setDiscount(t.getDiscount());
        courseDTO.setPrice(t.getPrice());
        courseDTO.setImage(t.getImage());
        courseDTO.setIsActive(t.getIsActive());
        courseDTO.setName(t.getName());
        courseDTO.setUpdatedDate(t.getUpdatedDate());

        TagDTO tagDTO = new TagDTO();
        tagDTO.setName(t.getTagId().getName());

        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(t.getTeacherId().getId());
        teacherDTO.setPosition(t.getTeacherId().getPosition());
        teacherDTO.setDescription(t.getTeacherId().getDescription());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(t.getTeacherId().getUserId().getId());
        userDTO.setUsername(t.getTeacherId().getUserId().getUsername());
        userDTO.setAvatar(t.getTeacherId().getUserId().getAvatar());
        userDTO.setEmail(t.getTeacherId().getUserId().getEmail());
        userDTO.setPhoneNumber(t.getTeacherId().getUserId().getPhoneNumber());

        courseDTO.setTag(tagDTO);

        teacherDTO.setUser(userDTO);

        courseDTO.setTeacher(teacherDTO);

        return courseDTO;
    }

    private List<CourseDTO> convertToDTO(List<Course> courses) {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course t : courses) {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(t.getId());
            courseDTO.setCreatedDate(t.getCreatedDate());
            courseDTO.setDescription(t.getDescription());
            courseDTO.setDiscount(t.getDiscount());
            courseDTO.setPrice(t.getPrice());
            courseDTO.setImage(t.getImage());
            courseDTO.setIsActive(t.getIsActive());
            courseDTO.setName(t.getName());
            courseDTO.setUpdatedDate(t.getUpdatedDate());

            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(t.getTeacherId().getId());
            teacherDTO.setPosition(t.getTeacherId().getPosition());
            teacherDTO.setDescription(t.getTeacherId().getDescription());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(t.getTeacherId().getUserId().getId());
            userDTO.setUsername(t.getTeacherId().getUserId().getUsername());
            userDTO.setAvatar(t.getTeacherId().getUserId().getAvatar());
            userDTO.setEmail(t.getTeacherId().getUserId().getEmail());
            userDTO.setPhoneNumber(t.getTeacherId().getUserId().getPhoneNumber());

            TagDTO tagDTO = new TagDTO();
            tagDTO.setName(t.getTagId().getName());

            courseDTO.setTag(tagDTO);

            teacherDTO.setUser(userDTO);

            courseDTO.setTeacher(teacherDTO);

            courseDTOList.add(courseDTO);
        }

        return courseDTOList;
    }

}
