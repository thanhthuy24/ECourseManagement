/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.repository.ProgressRepository;
import com.htt.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ProgressServiceImpl implements ProgressService{

    @Autowired
    private ProgressRepository progressRepo;
    
    @Override
    public float calculateCourseProgress(Long userId, Long courseId) {
        return this.progressRepo.calculateCourseProgress(userId, courseId);
    }
    
}
