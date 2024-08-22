/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.service;

import com.htt.pojo.Assignment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface AssignmentService {
    List<Assignment> getAssignments();
    void addOrUpdate(Assignment c);
    Assignment getAssignmentByLessonId(Long lessonId);
    Assignment getAssignmentById(Long id);
    void deleteAssignment(Long id);
}
