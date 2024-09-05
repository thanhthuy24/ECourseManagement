/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Answerchoice;
import com.htt.repository.AnswerchoiceRepository;
import com.htt.service.AnswerchoiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class AnswerchoiceServiceImpl implements AnswerchoiceService{
    
    @Autowired
    private AnswerchoiceRepository answerChoiceRepo;

    @Override
    public void addAnswerChoice(Answerchoice answerchoice, Long assignmentId, Long userId, Long questionId) {
        this.answerChoiceRepo.addAnswerChoice(answerchoice, assignmentId, userId, questionId);
    }

    @Override
    public List<Answerchoice> getAnswerchoiceByUserId(Long userId) {
        return this.answerChoiceRepo.getAnswerchoiceByUserId(userId);
    }

    @Override
    public List<Answerchoice> getAnswerchoiceByAssignmentAndUserId(Long userId, Long assignmentId, Long questionId) {
        return this.answerChoiceRepo.checkAnswers(userId, assignmentId, questionId);
    }
    
    @Override
    public List<Answerchoice> getAnswerchoices(Long userId, Long assignmentId) {
        return this.answerChoiceRepo.checkAnswer(userId, assignmentId);
    }
    
}
