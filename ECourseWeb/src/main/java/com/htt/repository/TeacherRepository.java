/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Teacher;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface TeacherRepository {
    List<Teacher> getTeachers();
    List<Teacher> getTeachers(Map<String, String> params);
    void addOrUpdate(Teacher c);
    Teacher getTeacherById(int id);
    void deleteTeacher(int id);
}
