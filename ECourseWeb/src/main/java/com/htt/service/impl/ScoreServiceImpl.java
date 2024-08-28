/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Answerchoice;
import com.htt.pojo.Score;
import com.htt.repository.AnswerchoiceRepository;
import com.htt.repository.ScoreRepository;
import com.htt.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ScoreServiceImpl implements ScoreService{
    @Autowired
    private ScoreRepository scoreRepo;
    
    @Override
    public void addScore(Long assignmentId, Long userId) {
        this.scoreRepo.addScore(assignmentId, userId);
    }

    @Override
    public Score getScoreByUserIdAndAssignment(Long assignmentId, Long userId) {
        return this.scoreRepo.getScoreByUserIdAndAssignment(assignmentId, userId);
    }

    @Override
    public void addScore2(Score score, Long assignmentId, Long userId) {
        this.scoreRepo.addScore2(score, assignmentId, userId);
    }
    
}
