/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Lesson;
import com.htt.repository.LessonRepository;
import com.htt.service.LessonService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class LessonServiceImpl implements LessonService{
    
    @Autowired
    private LessonRepository lessonRepo;

    @Override
    public List<Lesson> getLessons(Map<String, String> params) {
        return this.lessonRepo.getLessons(params);
    }

    @Override
    public void addOrUpdate(Lesson c) {
        this.lessonRepo.addOrUpdate(c);
    }

    @Override
    public Lesson getLessonById(int id) {
        return this.lessonRepo.getLessonById(id);
    }

    @Override
    public void deleteLesson(int id) {
        this.lessonRepo.deleteLesson(id);
    }
    
}
