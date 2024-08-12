/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

import com.htt.dto.TeacherDTO;
import com.htt.dto.UserDTO;
import com.htt.pojo.Teacher;
import com.htt.service.CourseService;
import com.htt.service.TeacherService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherSer;

    @DeleteMapping("/teachers/{teacherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "teacherId") Long id) {
        this.teacherSer.deleteTeacher(id);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDTO>> list() {
        List<Teacher> teachers = this.teacherSer.getTeachers();
        List<TeacherDTO> teacherDTO = convertToDTO1(teachers);
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
    }

    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<TeacherDTO> teacherInfor(@PathVariable(value = "teacherId") Long id) {
        Teacher teacher = this.teacherSer.getTeacherById(id);
        TeacherDTO teacherDTO = convertToDTO(teacher);
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setPosition(teacher.getPosition());
        teacherDTO.setDescription(teacher.getDescription());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(teacher.getUserId().getId());
        userDTO.setUsername(teacher.getUserId().getUsername());
        userDTO.setAvatar(teacher.getUserId().getAvatar());
        userDTO.setEmail(teacher.getUserId().getEmail());
        userDTO.setPhoneNumber(teacher.getUserId().getPhoneNumber());

        teacherDTO.setUser(userDTO);

        return teacherDTO;
    }

    private List<TeacherDTO> convertToDTO1(List<Teacher> teachers) {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        for (Teacher t : teachers) {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(t.getId());
            teacherDTO.setPosition(t.getPosition());
            teacherDTO.setDescription(t.getDescription());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(t.getUserId().getId());
            userDTO.setUsername(t.getUserId().getUsername());
            userDTO.setAvatar(t.getUserId().getAvatar());
            userDTO.setEmail(t.getUserId().getEmail());
            userDTO.setPhoneNumber(t.getUserId().getPhoneNumber());
            teacherDTO.setUser(userDTO);

            teacherDTOList.add(teacherDTO);
        }

        return teacherDTOList;
    }
}
