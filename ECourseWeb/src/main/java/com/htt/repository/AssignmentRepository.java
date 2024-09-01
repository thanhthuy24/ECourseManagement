/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Assignment;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface AssignmentRepository {
    List<Assignment> getAssignments();
    void addOrUpdate(Assignment c);
    List<Assignment> getAssignmentByCourseId(Long courseId);
    List<Assignment> getAssignmentByLessonId(Long lessonId);
    Assignment getAssignmentById(Long id);
    void deleteAssignment(Long id);
    
}
