/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Essay;
import com.htt.repository.EssayRepository;
import com.htt.service.EssayService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class EssayServiceImpl implements EssayService{
    
    @Autowired
    private EssayRepository essayRepo;

    @Override
    public void addEssay(Essay essay) {
        this.essayRepo.addEssay(essay);
    }

    @Override
    public List<Essay> getEssayByUserId(Long userId) {
        return this.essayRepo.getEssayByUserId(userId);
    }

    @Override
    public List<Essay> getEssayByQuestionId(Long questionId) {
        return this.essayRepo.getEssayByQuestionId(questionId);
    }

    @Override
    public List<Essay> getEssayByAssignmentId(Long assignmentId) {
        return this.essayRepo.getEssayByAssignmentId(assignmentId);
    }
    
}
