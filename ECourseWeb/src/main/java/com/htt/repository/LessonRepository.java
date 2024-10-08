/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.htt.repository;

import com.htt.pojo.Lesson;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface LessonRepository {
    List<Lesson> getLessons(Map<String, String> params);
    List<Lesson> getLessons();
    void addOrUpdate(Lesson c);
    Lesson getLessonById(Long id);
    void deleteLesson(Long id);
    List<Lesson> getLessonsByCourseId(Long courseId);
}
