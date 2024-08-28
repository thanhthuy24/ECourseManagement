/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.service.impl;

import com.htt.pojo.Choice;
import com.htt.repository.ChoiceRepository;
import com.htt.service.ChoiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ChoiceServiceImpl implements ChoiceService{
    
    @Autowired
    private ChoiceRepository choiceRepo;

    @Override
    public void addChoice(Choice choice) {
        this.choiceRepo.addChoice(choice);
    }

    @Override
    public List<Choice> getChoicesByQuestionId(Long questionId) {
        return this.choiceRepo.getChoicesByQuestionId(questionId);
    }

    @Override
    public List<Choice> getChoicesByCorrectByQuestionId(Long questionId) {
        return this.choiceRepo.getChoicesByCorrectByQuestionId(questionId);
    }
    
}
