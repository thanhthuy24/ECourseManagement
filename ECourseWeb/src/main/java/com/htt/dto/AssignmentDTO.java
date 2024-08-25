/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.dto;

import com.htt.pojo.Lesson;
import com.htt.pojo.Tag;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDTO {
    private String name;
    private Date createdDate;
    private Date dueDate;
    private TagDTO tag;
    private LessonDTO lesson;
    private CourseDTO course;
}
